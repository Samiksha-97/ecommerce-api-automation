pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run API Tests') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {
        always {
            junit '**/test-output/*.xml'
        }
    }
}