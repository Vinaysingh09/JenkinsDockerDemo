pipeline {
    agent any

    environment {
        DOCKER_HUB_CREDENTIALS = 'docker-hub-credentials-id'
        DOCKER_HUB_USERNAME = 'vinay020'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Package All Services') {
            steps {
                script {
                    def services = ['ServicePayment', 'ServiceUser','ServiceOrder'] // Add other services here

                    services.each { service ->
                        dir(service) {
                            echo "📦 Building ${service}"
                            sh 'mvn clean package -DskipTests'
                        }
                    }
                }
            }
        }

        stage('Build & Push Docker Images') {
            steps {
                script {
                    def services = ['ServicePayment', 'ServiceUser','ServiceOrder']
                    services.each { service ->
                        def image = "${DOCKER_HUB_USERNAME}/${service.toLowerCase()}:latest"
                        dir(service) {
                            echo "🐳 Building Docker Image for ${service}"
                            def dockerImage = docker.build(image)

                            docker.withRegistry('https://index.docker.io/v1/', DOCKER_HUB_CREDENTIALS) {
                                echo "📤 Pushing Docker Image for ${service}"
                                dockerImage.push()
                            }
                        }
                    }
                }
            }
        }

        stage('Deploy Locally with Docker Compose') {
            steps {
                echo "🚀 Deploying services using docker-compose"
                sh 'docker-compose down || true'
                sh 'docker-compose up -d --build'
            }
        }
    }

    post {
        success {
            echo "✅ Pipeline completed successfully"
        }
        failure {
            echo "❌ Pipeline failed"
        }
        always {
            echo "🧹 Cleaning up workspace"
        }
    }
}