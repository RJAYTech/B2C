pipeline {
    agent any


    environment {
        GITHUB_REPO_URL = 'https://github.com/RJAYTech/B2C.git'
        ARTIFACTORY_IP = '43.204.202.141'
        ARTIFACTORY_SERVER_ID = 'b2b_artifactory'
        REPOSITORY_PATH = 'b2b'
        JAR_ARTIFACT_PATH = '/var/lib/jenkins/workspace/B2B/ecommerce-multivendor-backend-master/target/ecommerce-backend-0.0.1-SNAPSHOT.jar'
        DOCKER_HUB_USERNAME = 'rjayb2b'
        DOCKER_IMAGE_NAME = 'backend'
        DOCKER_IMAGE_TAG = 'latest'
        DOCKER_CONTAINER_NAME = 'backend_container'
    }

    stages {
       

        stage('Sonar Analysis') {
            steps {
                dir('ecommerce-multivendor-backend-master') {
                    sh '''mvn sonar:sonar \
                        -Dsonar.host.url=http://15.207.180.250:9000/ \
                        -Dsonar.login=squ_242ed5e6966472b5c51733f9d5ed16f48665c1b1 \
                        -Dsonar.projectName=b2b \
                        -Dsonar.java.binaries=. \
                        -Dsonar.projectKey=b2b'''
                }
            }
        }

        stage('Maven Build') {
            steps {
                dir('ecommerce-multivendor-backend-master') {
                    sh 'mvn clean'
                    sh 'mvn clean package'
                }
            }
        }

        stage('OWASP Dependency Check') {
            steps {
                script {
                    def dependencyCheckResults = dependencyCheck scanPaths: '/var/lib/jenkins/workspace/B2B/ecommerce-multivendor-backend-master/target', odcInstallation: 'owasp'
                    archiveArtifacts artifacts: '**/dependency-check-report.xml'
                }
            }
        }

        stage('Upload to S3') {
            steps {
                withCredentials([aws(credentialsId: 'AwsCred', variable: 'AWS_CREDENTIALS')]) {
                    sh 'aws s3 cp /var/lib/jenkins/workspace/B2B/ecommerce-multivendor-backend-master/target/ecommerce-backend-0.0.1-SNAPSHOT.jar s3://b2bjenkins/'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir('ecommerce-multivendor-backend-master') {
                    sh "docker build -t ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG} ."
                    sh "docker tag ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG} ${DOCKER_HUB_USERNAME}/${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}"
                }
            }
        }

        stage('Trivy Scan') {
            steps {
                script {
                    def trivyScan = sh(script: "trivy image ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}", returnStdout: true).trim()
                    writeFile file: 'trivy_scan_report.txt', text: trivyScan
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'DockerCred', usernameVariable: 'DOCKER_HUB_USERNAME', passwordVariable: 'DOCKER_HUB_PASSWORD')]) {
                    sh "docker login -u ${DOCKER_HUB_USERNAME} -p ${DOCKER_HUB_PASSWORD}"
                    sh "docker push ${DOCKER_HUB_USERNAME}/${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}"
                }
            }
        }

        stage('Pull Latest Image and Run Container') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'DockerCred', usernameVariable: 'DOCKER_HUB_USERNAME', passwordVariable: 'DOCKER_HUB_PASSWORD')]) {
                    sh "docker login -u ${DOCKER_HUB_USERNAME} -p ${DOCKER_HUB_PASSWORD}"
                    sh "docker pull ${DOCKER_HUB_USERNAME}/${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}"
                    sh "docker stop ${DOCKER_CONTAINER_NAME} || true"
                    sh "docker rm ${DOCKER_CONTAINER_NAME} || true"
                    sh "docker run --name ${DOCKER_CONTAINER_NAME} -d -p 8081:8081 ${DOCKER_HUB_USERNAME}/${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}"
                }
            }
        }
    }

    post {
        success {
            emailext (
                subject: "Docker build succeeded: ${currentBuild.fullDisplayName}",
                body: "The Docker build ${currentBuild.fullDisplayName} succeeded.",
                to: "Vinai.k@rjaytechnologies.com",
                from: "kollurivinaichowdary@gmail.com",
                attachmentsPattern: 'trivy_scan_report.txt'
            )
        }
        failure {
            emailext (
                subject: "Docker build failed: ${currentBuild.fullDisplayName}",
                body: "The Docker build ${currentBuild.fullDisplayName} failed. Please check the attached console output for more details.",
                to: "Vinai.k@rjaytechnologies.com",
                from: "kollurivinaichowdary@gmail.com",
                attachLog: true,
                attachmentsPattern: 'console.log'
            )
        }
    }
}
