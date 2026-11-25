# HEALTH2 - DEVOPS PARTIE 11

## Architecture

Health2 Backend
       |
       v
Spring Boot Actuator
       |
       v
Micrometer Prometheus
       |
       v
Prometheus
       |
       +------ Grafana
       |
       +------ Alertmanager

Kubernetes :
- Node Exporter
- Kube State Metrics
- Prometheus Operator

## Installation

Ajouter le depot Helm :

helm repo add prometheus-community \
https://prometheus-community.github.io/helm-charts

Mettre a jour :

helm repo update

Creer le namespace :

kubectl apply -f deploy/monitoring/namespace.yaml

Creer le secret administrateur Grafana
avant le deploiement :

kubectl create secret generic health2-grafana-admin \
  --namespace monitoring \
  --from-literal=admin-user=health2-admin \
  --from-literal=admin-password='<MOT_DE_PASSE_FORT>'

Ne pas mettre le vrai mot de passe dans Git
ou dans un historique de terminal partage.

Installer la stack :

helm upgrade --install health2-monitoring \
prometheus-community/kube-prometheus-stack \
--namespace monitoring \
--version '<VERSION_VALIDEE>' \
-f deploy/monitoring/prometheus/values.yaml \
--wait

Remplacer VERSION_VALIDEE par une version
du chart testee et approuvee.

## Activer le monitoring du backend

Le backend Spring Boot doit inclure :

- spring-boot-starter-actuator
- micrometer-registry-prometheus

Exemple de configuration :

management:
  endpoints:
    web:
      exposure:
        include: health,prometheus

  endpoint:
    health:
      show-details: never

  prometheus:
    metrics:
      export:
        enabled: true

Ne pas exposer les endpoints de management
publiquement via la Gateway.

## Deployer les regles

kubectl apply -f \
deploy/monitoring/prometheus/health2-alerts.yaml

## Deployer le ServiceMonitor

kubectl apply -f \
deploy/monitoring/servicemonitor/health2-backend.yaml

## Verifications

kubectl get pods -n monitoring

kubectl get services -n monitoring

kubectl get prometheusrules -n monitoring

kubectl get servicemonitors -n monitoring

kubectl get pvc -n monitoring

## Grafana

Acces temporaire :

kubectl port-forward \
-n monitoring \
svc/health2-monitoring-grafana 3000:80

## Production

Prevoir :

- Stockage persistant.
- Retention des metriques.
- Sauvegarde des dashboards.
- Gestion des secrets.
- Authentification.
- Controle des acces.
- Supervision de Prometheus.
- Alertes de disponibilite.
- Tests de restauration.

## Confidentialite

Ne jamais collecter :

- Nom des patients.
- Adresse email.
- Poids individuel.
- Donnees de sommeil individuelles.
- Tokens d'authentification.
- Contenu des requetes HTTP.
- Informations medicales identifiantes.

Les metriques doivent rester agregees
et depourvues de donnees personnelles.
