def call(string repourl){
    pipeline{
        agent any
        tools{
        maven 'M2_HOME'
        jdk 'JAVA_HOME'
    }
    stages{
        stage('Tools Initialzation'){
            steps{
                sh 'mvn --version'
                sh 'java -version'
            }
        }
        stage('checkout code'){
            stpes{
                git branch: 'master',
                url : "${repourl}"
            }
        }
        stage('cleaning workspace'){
            steps{
                sh 'mvn clean'
            }
        }
        stage('Running test case'){
            steps{
                sh 'mvn test'
            }
        }
        stage('packaging application'){
            steps{
                sh 'mvn package -DskipTests'
            }
        }
    }
    }
}