CREATE TABLE organizations (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name        VARCHAR(100)  NOT NULL,
    slug        VARCHAR(50)   NOT NULL UNIQUE,
    plan        VARCHAR(20)   NOT NULL DEFAULT 'FREE',
    created_at  TIMESTAMPTZ   NOT NULL DEFAULT now(),
    is_active   BOOLEAN       NOT NULL DEFAULT true
);

CREATE INDEX idx_organizations_slug ON organizations(slug);