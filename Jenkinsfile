pipeline {

    agent any

    parameters {
        choice(
            name: 'TEST_SUITE',
            choices: ['smoke', 'regression'],
            description: 'Select the API test suite to execute'
        )
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run API Tests') {
            steps {
                script {

                    if (params.TEST_SUITE == 'smoke') {

                        bat 'mvn clean test -DsuiteXmlFile=smoke.xml'

                    } else if (params.TEST_SUITE == 'regression') {

                        bat 'mvn clean test -DsuiteXmlFile=regression.xml'

                    }

                }
            }
        }
    }

    post {

        always {

            allure([
                includeProperties: false,
                jdk: '',
                results: [[path: 'target/allure-results']]
            ])

        }
    }
}