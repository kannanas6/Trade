pipeline {
  agent any

  environment {
    // Replace with your GCP project ID and cluster details.
    GCP_PROJECT = "my-first-project-324400"
    CLUSTER_NAME = "autopilot-cluster-1"
    CLUSTER_ZONE = "us-central1-a"
    REGISTRY = "gcr.io/${GCP_PROJECT}"
    IMAGE_NAME = "gcpdeploy_api_service"
    // This credential ID should match the one stored in Jenkins for your GCP service account JSON key.
    GOOGLE_APPLICATION_CREDENTIALS = credentials('FileGCPAccountDefaultKey')
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build Docker Image') {
      steps {
        // Build the Docker image. (Ensure your Dockerfile is in the repo.)
        sh 'docker-compose build'
      }
    }

    stage('Tag & Push Image') {
      steps {
        script {
          // Tag the built image for GCR
          sh "docker tag ${IMAGE_NAME}:latest ${REGISTRY}/${IMAGE_NAME}:latest"
          // Push the image to Google Container Registry
          sh "docker push ${REGISTRY}/${IMAGE_NAME}:latest"
        }
      }
    }

    stage('Convert Compose to Kubernetes Manifests') {
      steps {
        // Convert your docker-compose.yml into Kubernetes manifests using Kompose.
        sh 'kompose convert -f docker-compose.yml -o k8s-manifests'
      // Verify the generated files exist
        sh 'ls -lah k8s-manifests'
        // Update the generated YAML to use the image in GCR.
        sh "sed -i 's|${IMAGE_NAME}:latest|${REGISTRY}/${IMAGE_NAME}:latest|g' api-service-deployment.yaml"
      }
    }

    stage('Deploy to GKE') {
      steps {
        script {
          // Authenticate with GCP using the service account key.
          sh "gcloud auth activate-service-account --key-file=${GOOGLE_APPLICATION_CREDENTIALS}"
          // Set the current project.
          sh "gcloud config set project ${GCP_PROJECT}"
          // Get credentials for your GKE cluster.
          sh "gcloud container clusters get-credentials ${CLUSTER_NAME} --zone ${CLUSTER_ZONE} --project ${GCP_PROJECT}"
          // Deploy the generated Kubernetes manifests.
          sh 'kubectl apply -f .'
        }
      }
    }
  }

  post {
    always {
      echo 'Pipeline completed.'
    }
  }
}
