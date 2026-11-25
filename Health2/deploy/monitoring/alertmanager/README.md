# HEALTH2 - ALERTMANAGER

## Objectif

Centraliser les alertes Prometheus.

## Alertes Health2

- BackendDown.
- HighHTTP5xxRate.
- PodRestarting.
- PodNotReady.
- HighCPUUsage.
- HighMemoryUsage.
- PrometheusTargetDown.

## Canaux de notification

A configurer selon les besoins :

- Email.
- Slack.
- Microsoft Teams via integration adaptee.
- PagerDuty.
- Webhook securise.

## Regles

Critical :

Notification immediate.

Warning :

Notification selon la politique d'astreinte.

## Securite

Les tokens, mots de passe SMTP et secrets
de notification ne doivent jamais etre stockes
dans les fichiers YAML publics.

Utiliser des Kubernetes Secrets.

## Verification

kubectl get pods -n monitoring

kubectl get prometheusrules -n monitoring

kubectl get servicemonitors -n monitoring
