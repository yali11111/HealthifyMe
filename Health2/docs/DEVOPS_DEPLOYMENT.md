# HEALTH2 - DEVOPS PARTIE 12

## Objectif

Deployment Kubernetes avec Helm.

## Prerequis

- Cluster Kubernetes disponible.
- kubectl configure.
- Helm installe.
- Image backend publiee dans un registry.
- Metrics Server installe pour HPA.
- Secret health2-backend-secrets cree.
- Au moins deux nodes Kubernetes pour une HA multi-node.
- Gateway API et Envoy Gateway configures.

## 1. Creer les namespaces

kubectl apply -f deploy/kubernetes/namespaces/

## 2. Creer les secrets backend

Creer le secret health2-backend-secrets dans chaque namespace.

Les noms des variables doivent correspondre
aux attentes du backend.

Ne jamais stocker les mots de passe dans Git.

## 3. Verifier Helm

helm lint deploy/kubernetes/helm/health2

## 4. Verifier le rendu DEV

helm template health2 \
deploy/kubernetes/helm/health2 \
--namespace health2-dev \
-f deploy/kubernetes/helm/health2/values-dev.yaml

## 5. Installer DEV

helm upgrade --install health2 \
deploy/kubernetes/helm/health2 \
--namespace health2-dev \
-f deploy/kubernetes/helm/health2/values-dev.yaml \
--set image.repository=REGISTRY/health2-backend \
--set image.tag=VERSION \
--wait

## 6. Installer STAGING

helm upgrade --install health2 \
deploy/kubernetes/helm/health2 \
--namespace health2-staging \
-f deploy/kubernetes/helm/health2/values-staging.yaml \
--set image.repository=REGISTRY/health2-backend \
--set image.tag=VERSION \
--wait

## 7. Installer PRODUCTION

Utiliser une image immuable identifiee par son digest
ou une version release validee.

helm upgrade --install health2 \
deploy/kubernetes/helm/health2 \
--namespace health2 \
-f deploy/kubernetes/helm/health2/values-production.yaml \
--set image.repository=REGISTRY/health2-backend \
--set image.tag=VERSION \
--wait \
--timeout 10m

## 8. Verifications

kubectl get deployments -n health2

kubectl get pods -n health2

kubectl get services -n health2

kubectl get hpa -n health2

kubectl get pdb -n health2

kubectl get events -n health2 --sort-by=.lastTimestamp

## 9. Scaling manuel

kubectl scale deployment health2-health2 \
-n health2 \
--replicas=4

Attention : le HPA peut ensuite modifier ce nombre.

## 10. Consulter le HPA

kubectl get hpa -n health2

kubectl describe hpa -n health2

## 11. Rollback

helm history health2 -n health2

helm rollback health2 REVISION -n health2

## 12. HA

Prevoir :

- Plusieurs nodes.
- Plusieurs zones de disponibilite si possible.
- Au moins trois replicas en production.
- PodDisruptionBudget.
- Topology Spread Constraints.
- Readiness et liveness probes.
- Stockage PostgreSQL persistant et sauvegarde.
- Tests de panne.

## 13. Gateway API

Le routage externe doit etre gere par
la Gateway API et le controleur Envoy Gateway
deja prepares dans la partie 09.

Le Service Health2 reste de type ClusterIP.

## 14. Monitoring

Verifier l'integration avec :

- Prometheus.
- Grafana.
- Alertmanager.
- ServiceMonitor.

## 15. Securite

- Images scannees.
- Images non privilegiees.
- Secrets hors Git.
- HTTPS en entree.
- RBAC minimal.
- Network Policies.
- Mises a jour regulieres.
- Audit des acces.

## IMPORTANT

Le chart est un modele de deploiement.

Il doit etre valide avec le backend,
le registry, les variables d'environnement,
les endpoints de health et le cluster reels
avant toute utilisation en production.
