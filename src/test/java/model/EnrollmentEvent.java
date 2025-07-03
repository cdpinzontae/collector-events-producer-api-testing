package model;

import java.time.Instant;
import java.util.UUID;

public class EnrollmentEvent {
    private String id;
    private String eventType;
    private Data data;
    private Meta meta;

    public static class Data {
        private String emailAddress;
        private String language;
        private String scheme;
        private String issuerCode;
        private String channel;
        private boolean emailOptIn;
        private String cardNumber;

        // Getters y Setters
        public String getEmailAddress() { return emailAddress; }
        public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }

        public String getLanguage() { return language; }
        public void setLanguage(String language) { this.language = language; }

        public String getScheme() { return scheme; }
        public void setScheme(String scheme) { this.scheme = scheme; }

        public String getIssuerCode() { return issuerCode; }
        public void setIssuerCode(String issuerCode) { this.issuerCode = issuerCode; }

        public String getChannel() { return channel; }
        public void setChannel(String channel) { this.channel = channel; }

        public boolean isEmailOptIn() { return emailOptIn; }
        public void setEmailOptIn(boolean emailOptIn) { this.emailOptIn = emailOptIn; }

        public String getCardNumber() { return cardNumber; }
        public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    }

    public static class Meta {
        private String partitionKey;
        private String originClient;
        private String correlationId;
        private String version;
        private Instant timestamp;

        // Getters y Setters
        public String getPartitionKey() { return partitionKey; }
        public void setPartitionKey(String partitionKey) { this.partitionKey = partitionKey; }

        public String getOriginClient() { return originClient; }
        public void setOriginClient(String originClient) { this.originClient = originClient; }

        public String getCorrelationId() { return correlationId; }
        public void setCorrelationId(String correlationId) { this.correlationId = correlationId; }

        public String getVersion() { return version; }
        public void setVersion(String version) { this.version = version; }

        public Instant getTimestamp() { return timestamp; }
        public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
    }

    // Getters y Setters del nivel superior
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public Data getData() { return data; }
    public void setData(Data data) { this.data = data; }

    public Meta getMeta() { return meta; }
    public void setMeta(Meta meta) { this.meta = meta; }
}
