package com.tcc.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties("chronosapi")
public class ChronosApiProperties {

    private final Seguranca seguranca = new Seguranca();

    private final S3 s3 = new S3();

    private final DataSouceTenant dataSouceTenant = new DataSouceTenant();

    private final PoolConexao poolConexao = new PoolConexao();

    private final StorageLocal storageLocal = new StorageLocal();

    //host definido para controle do cors
    private String[] originsPermitidas = {""};
    private String entity;

    private boolean desenvolvimento = false;


    public Seguranca getSeguranca() {
        return seguranca;
    }

    public String[] getOriginsPermitidas() {
        return originsPermitidas;
    }

    public void setOriginsPermitidas(String[] originsPermitidas) {
        this.originsPermitidas = originsPermitidas;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public boolean isDesenvolvimento() {
        return desenvolvimento;
    }

    public void setDesenvolvimento(boolean desenvolvimento) {
        this.desenvolvimento = desenvolvimento;
    }

    public S3 getS3() {
        return s3;
    }


    public DataSouceTenant getDataSouceTenant() {
        return dataSouceTenant;
    }

    public PoolConexao getPoolConexao() {
        return poolConexao;
    }

    public StorageLocal getStorageLocal() {
        return storageLocal;
    }


    public static class Seguranca {

        private boolean enableHttps;

        public boolean isEnableHttps() {
            return enableHttps;
        }

        public void setEnableHttps(boolean enableHttps) {
            this.enableHttps = enableHttps;
        }

    }

    public static class StorageLocal {

        private String local;
        private String urlBase;


        public String getLocal() {
            return local;
        }

        public void setLocal(String local) {
            this.local = local;
        }

        public String getUrlBase() {
            return urlBase;
        }

        public void setUrlBase(String urlBase) {
            this.urlBase = urlBase;
        }
    }

    public static class S3 {
        private final String bucket = "chronosinfo-gdoce-arquivos";
        private String accessKeyId;
        private String secretAccessKey;

        public String getAccessKeyId() {
            return accessKeyId;
        }

        public void setAccessKeyId(String accessKeyId) {
            this.accessKeyId = accessKeyId;
        }

        public String getSecretAccessKey() {
            return secretAccessKey;
        }

        public void setSecretAccessKey(String secretAccessKey) {
            this.secretAccessKey = secretAccessKey;
        }

        public String getBucket() {
            return bucket;
        }
    }

    public static class DataSouceTenant {
        private String url;
        private String username;
        private String password;
        private String driver;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getDriver() {
            return driver;
        }

        public void setDriver(String driver) {
            this.driver = driver;
        }
    }

    @Getter
    @Setter
    public static class PoolConexao {
        private int minPoolSize = 10;

        private int maxPoolSize = 10;

        private int cachSize;

        private int cachLimit;

        private long timeOut = 30000;

    }
}
