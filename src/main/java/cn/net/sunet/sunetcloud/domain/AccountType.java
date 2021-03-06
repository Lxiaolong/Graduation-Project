package cn.net.sunet.sunetcloud.domain;

public class AccountType {
    private Integer typeId;

    private String accountType;

    private String roles;

    private Byte add;

    private Byte delete;

    private Byte query;

    private Byte modify;

    private Byte config;

    public Integer getId() {
        return typeId;
    }

    public void setId(Integer id) {
        this.typeId = id;
    }

    public String getName() {
        return accountType;
    }

    public void setName(String name) {
        this.accountType = name == null ? null : name.trim();
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles == null ? null : roles.trim();
    }

    public Byte getAdd() {
        return add;
    }

    public void setAdd(Byte add) {
        this.add = add;
    }

    public Byte getDelete() {
        return delete;
    }

    public void setDelete(Byte delete) {
        this.delete = delete;
    }

    public Byte getQuery() {
        return query;
    }

    public void setQuery(Byte query) {
        this.query = query;
    }

    public Byte getModify() {
        return modify;
    }

    public void setModify(Byte modify) {
        this.modify = modify;
    }

    public Byte getConfig() {
        return config;
    }

    public void setConfig(Byte config) {
        this.config = config;
    }
}