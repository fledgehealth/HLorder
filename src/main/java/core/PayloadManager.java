package core;


import java.util.List;
import java.util.Map;

public class PayloadManager {

    private static PayloadManager instance;

    private PayloadManager() {}

    public static PayloadManager getInstance() {
        if (instance == null) {
            instance = new PayloadManager();
        }
        return instance;
    }

    // =================== REQUEST PAYLOAD FIELDS ===================

    private String partnerOrderId;
    private String partnerOrderNo;
    private String customerName;
    private String patientName;
    private String mobileNo;
    private String emailId;
    private String state;
    private String city;
    private String zipcode;
    private double lat;
    private double lon;
    private String address1;
    private String address2;
    private String paymentModeOrder;
    private Map<String, Object> paymentModeItem;
    private String orderType;
    private double collectible;
    private double shippingCharges;
    private List<Map<String, Object>> orderDetails;
    private List<String> prescriptionLinks;
    private String webhookUrl;
    private double discount;
    private List<Map<String, Object>> data;

    // =================== RESPONSE FIELDS ===================

    private String status;
    private String statusCode;
    private String orderNumber;
    private int responseId;
    private String pharmacyId;
    private String pharmacyName;
    private int erpId;
    private String erpApiKey;
    private double responseLat;
    private double responseLong;
    private boolean isDisabled;
    private double distance;

    // ========== Getters and Setters for All Fields ==========

    public String getPartnerOrderId() { return partnerOrderId; }
    public void setPartnerOrderId(String partnerOrderId) { this.partnerOrderId = partnerOrderId; }

    public String getPartnerOrderNo() { return partnerOrderNo; }
    public void setPartnerOrderNo(String partnerOrderNo) { this.partnerOrderNo = partnerOrderNo; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public String getMobileNo() { return mobileNo; }
    public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }

    public String getEmailId() { return emailId; }
    public void setEmailId(String emailId) { this.emailId = emailId; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getZipcode() { return zipcode; }
    public void setZipcode(String zipcode) { this.zipcode = zipcode; }

    public double getLat() { return lat; }
    public void setLat(double lat) { this.lat = lat; }

    public double getLon() { return lon; }
    public void setLon(double lon) { this.lon = lon; }

    public String getAddress1() { return address1; }
    public void setAddress1(String address1) { this.address1 = address1; }

    public String getAddress2() { return address2; }
    public void setAddress2(String address2) { this.address2 = address2; }

    public String getPaymentModeOrder() { return paymentModeOrder; }
    public void setPaymentModeOrder(String paymentModeOrder) { this.paymentModeOrder = paymentModeOrder; }

    public Map<String, Object> getPaymentModeItem() { return paymentModeItem; }
    public void setPaymentModeItem(Map<String, Object> paymentModeItem) { this.paymentModeItem = paymentModeItem; }

    public String getOrderType() { return orderType; }
    public void setOrderType(String orderType) { this.orderType = orderType; }

    public double getCollectible() { return collectible; }
    public void setCollectible(double collectible) { this.collectible = collectible; }

    public double getShippingCharges() { return shippingCharges; }
    public void setShippingCharges(double shippingCharges) { this.shippingCharges = shippingCharges; }

    public List<Map<String, Object>> getOrderDetails() { return orderDetails; }
    public void setOrderDetails(List<Map<String, Object>> orderDetails) { this.orderDetails = orderDetails; }

    public List<String> getPrescriptionLinks() { return prescriptionLinks; }
    public void setPrescriptionLinks(List<String> prescriptionLinks) { this.prescriptionLinks = prescriptionLinks; }

    public String getWebhookUrl() { return webhookUrl; }
    public void setWebhookUrl(String webhookUrl) { this.webhookUrl = webhookUrl; }

    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getStatusCode() { return statusCode; }
    public void setStatusCode(String statusCode) { this.statusCode = statusCode; }

    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }

    public int getResponseId() { return responseId; }
    public void setResponseId(int responseId) { this.responseId = responseId; }

    public String getPharmacyId() { return pharmacyId; }
    public void setPharmacyId(String pharmacyId) { this.pharmacyId = pharmacyId; }

    public String getPharmacyName() { return pharmacyName; }
    public void setPharmacyName(String pharmacyName) { this.pharmacyName = pharmacyName; }

    public int getErpId() { return erpId; }
    public void setErpId(int erpId) { this.erpId = erpId; }

    public String getErpApiKey() { return erpApiKey; }
    public void setErpApiKey(String erpApiKey) { this.erpApiKey = erpApiKey; }

    public double getResponseLat() { return responseLat; }
    public void setResponseLat(double responseLat) { this.responseLat = responseLat; }

    public double getResponseLong() { return responseLong; }
    public void setResponseLong(double responseLong) { this.responseLong = responseLong; }

    public boolean isDisabled() { return isDisabled; }
    public void setDisabled(boolean disabled) { isDisabled = disabled; }

    public double getDistance() { return distance; }
    public void setDistance(double distance) { this.distance = distance; }

    @Override
    public String toString() {
        return "PayloadManager{" +
                "partnerOrderId='" + partnerOrderId + '\'' +
                ", partnerOrderNo='" + partnerOrderNo + '\'' +
                ", customerName='" + customerName + '\'' +
                ", patientName='" + patientName + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", emailId='" + emailId + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", paymentModeOrder='" + paymentModeOrder + '\'' +
                ", paymentModeItem=" + paymentModeItem +
                ", orderType='" + orderType + '\'' +
                ", collectible=" + collectible +
                ", shippingCharges=" + shippingCharges +
                ", orderDetails=" + orderDetails +
                ", prescriptionLinks=" + prescriptionLinks +
                ", webhookUrl='" + webhookUrl + '\'' +
                ", discount=" + discount +
                '}';
    }
}
