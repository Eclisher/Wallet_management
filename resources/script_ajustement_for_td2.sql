-- 1.a) Créer la table `category` avec une contrainte UNIQUE sur la colonne `name`.
CREATE TABLE IF NOT EXISTS category (
                                        id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                                        name VARCHAR(255) NOT NULL,
                                        type VARCHAR(50) NOT NULL CHECK (type IN ('EXPENSE', 'INCOME')),
                                        CONSTRAINT unique_category_name UNIQUE (name)
);

-- 1.b) Ajouter la colonne `category_id` à la table `transaction` pour référencer la catégorie associée.
ALTER TABLE "transaction"
    ADD COLUMN "category_id" UUID REFERENCES "category"("id");


-- 1.c) Ajouter la colonne `type` à la table `category` pour spécifier si c'est une dépense ou une entrée.
-- La colonne `type` est une contrainte CHECK qui assure que la valeur est 'EXPENSE' ou 'INCOME'.
-- Exemple d'insertion de catégorie de dépense
INSERT INTO category ( name, type)
VALUES ( 'Restaurant', 'EXPENSE')
ON CONFLICT (id) DO NOTHING;

-- Exemple d'insertion de catégorie d'entrée d'argent
INSERT INTO category ( name, type)
VALUES ( 'Salaire', 'INCOME')
ON CONFLICT (id) DO NOTHING;



