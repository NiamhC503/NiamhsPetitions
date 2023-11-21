pipeline {
    agent any

    parameters {
        choice(name: 'DEPLOY', choices: ['Yes, Deploy', 'No, Do Not Deploy'], description: 'Would you like to deploy?')
    }
    stages {
        stage ('GetProject') {
            steps {
                git 'https://github.com/NiamhC503/NiamhsPetitions.git'

            }
        }
        stage ('build') {
            steps {
                sh 'mvn clean'

            }
        }
        stage ('Package') {
            steps {
                sh 'mvn package'
            }
        }
        stage ('Archive') {
            steps {
                archiveArtifacts allowEmptyArchive: true,
                    artifacts: '**/niamhspetitions*.war'
            }
        }
        stage ('Deploy') {
            when {
                expression {params.DEPLOY == 'Yes, Deploy' }
            }
            steps {
                sh 'docker build -f Dockerfile -t myapp . '
                sh 'docker rm -f "myappcontainer" || true'
                sh 'docker run --name "myappcontainer" -p 9090:8080 --detach myapp:latest'
            }
        }
    }
}