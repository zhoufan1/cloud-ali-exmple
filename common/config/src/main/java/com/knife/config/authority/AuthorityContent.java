package com.knife.config.authority;

public final class AuthorityContent {
    private AuthoritySession authoritySession;
    private static final ThreadLocal<AuthorityContent> holder = ThreadLocal.withInitial(AuthorityContent::new);

    public static AuthorityContent get() {
        return holder.get();
    }

    public AuthoritySession getAuthoritySession() {
        return authoritySession;
    }

    public void setAuthoritySession(AuthoritySession authoritySession) {
        this.authoritySession = authoritySession;
    }

    public void clear() {
        holder.remove();
    }

}
