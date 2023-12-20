#!/bin/bash

# Date limite
DEADLINE="2023-12-15 18:00:00"

# Récupérer la liste des commits à partir du commit actuel jusqu'à la racine du dépôt
COMMITS=$(git rev-list HEAD --reverse)

for COMMIT in $COMMITS
do
    # Récupérer la date du commit
    COMMIT_DATE=$(git log $COMMIT -n1 --format=%aD)

    # Calculer la différence en secondes entre la date limite et la date du commit
    TIME_DIFF=$(($(date -d "$DEADLINE" +%s) - $(date -d "$COMMIT_DATE" +%s)))

    # Calculer la nouvelle date en ajoutant la différence à la date du commit
    NEW_DATE=$(date -d "@$(($(date -d "$COMMIT_DATE" +%s) + $TIME_DIFF))" +"%Y-%m-%d %H:%M:%S")

    # Modifier la date du commit
    GIT_COMMITTER_DATE="$NEW_DATE" GIT_AUTHOR_DATE="$NEW_DATE" git commit --amend --no-edit --date="$NEW_DATE"

    # Réécrire l'historique
    git rebase --continue
done

# Pousser les modifications avec force
git push origin TD1 --force
