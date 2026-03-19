CREATE TABLE organization_memberships (
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    organization_id UUID        NOT NULL REFERENCES organizations(id),
    user_id         UUID        NOT NULL REFERENCES users(id),
    role            VARCHAR(20) NOT NULL DEFAULT 'MEMBER',
    joined_at       TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT uq_org_user UNIQUE (organization_id, user_id)
);

CREATE INDEX idx_memberships_org_id ON organization_memberships(organization_id);
CREATE INDEX idx_memberships_user_id ON organization_memberships(user_id);