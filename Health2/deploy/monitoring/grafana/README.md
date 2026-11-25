# HEALTH2 - GRAFANA

## Objectif

Visualiser les performances de Health2.

## Tableaux de bord recommandes

### Infrastructure

- CPU.
- RAM.
- Disque.
- Reseau.
- Charge des nodes.

### Kubernetes

- Nombre de pods.
- Pods non disponibles.
- Redemarrages.
- CPU par namespace.
- RAM par namespace.

### Backend Spring Boot

- Requetes HTTP.
- Temps de reponse.
- Erreurs HTTP.
- JVM Memory.
- Garbage Collection.
- Threads actifs.
- Connexions database.

## Acces

Grafana est expose en ClusterIP.

Utiliser un acces administratif securise,
par exemple un tunnel kubectl :

kubectl port-forward \
  -n monitoring \
  svc/health2-monitoring-grafana 3000:80

Adresse locale :

http://localhost:3000

## Securite

- Ne pas exposer Grafana directement sur Internet.
- Utiliser un compte administrateur protege.
- Activer une authentification adaptee.
- Ne pas partager les dashboards contenant
  des informations sensibles.
- Ne jamais placer les mots de passe dans Git.

Le secret health2-grafana-admin doit etre
cree avant le deploiement Helm.
