package tests;

import api.PlaceOrderAPI;
import core.PayloadManager;

import org.json.JSONObject;
import org.testng.annotations.Test;
import utils.DBUtil;

import java.util.*;
import org.testng.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class PlaceOrderTest {

    private static ExtentReports extent;
    private static ExtentTest test;
    private static PayloadManager pmInstance;

    static {
        // Initialize ExtentReports
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("Test Report");
        sparkReporter.config().setReportName("PlaceOrder API Test Report");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    @Test
    public void testPlaceOrderAPI() {
        test = extent.createTest("testPlaceOrderAPI", "Test the PlaceOrder API functionality");

        try {
            pmInstance = PayloadManager.getInstance();

            // Generate unique partner order ID and number for each test run
            String uniqueId = UUID.randomUUID().toString();
            pmInstance.setPartnerOrderId("OR_AT" + uniqueId);
            pmInstance.setPartnerOrderNo("OR_AT" + uniqueId);

            // Set payload values
            test.info("Setting payload values...");
            pmInstance.setCustomerName("Saurabh");
            pmInstance.setPatientName("Saurabh");
            pmInstance.setMobileNo("7379406027");
            pmInstance.setEmailId("coo@fledgehealth.com");
            pmInstance.setState("Uttar Pradesh");
            pmInstance.setCity("Greater Noida");
            pmInstance.setZipcode("122001");
            pmInstance.setLat(11.2419);
            pmInstance.setLon(75.77661);
            pmInstance.setAddress1("2625 26th floor, Tulip Tower");
            pmInstance.setAddress2("ETA 2 gretaer noida west");
            pmInstance.setPaymentModeOrder("Prepaid");

            test.info("Payload after setting basic details: " + pmInstance);

            Map<String, Object> paymentModeItem = new HashMap<>();
            paymentModeItem.put("payment_verification_order_id", 39849023);

            Map<String, Object> paymentDetails = new HashMap<>();
            paymentDetails.put("payment_status", 1);
            paymentDetails.put("transaction_id", "39849023");
            paymentDetails.put("amount", 0.0);
            paymentModeItem.put("payment_details", Collections.singletonList(paymentDetails));

            pmInstance.setPaymentModeItem(paymentModeItem);
            pmInstance.setOrderType("SDD_NDD");
            pmInstance.setCollectible(145.0);
            pmInstance.setShippingCharges(40.0);

            test.info("Payload after setting payment details: " + pmInstance);

            Map<String, Object> orderDetail = new HashMap<>();
            orderDetail.put("partner_sku_code", "10234972");
            orderDetail.put("sku_name", "Dolo-650 Mg Tablet");
            orderDetail.put("fh_sku_type", "Oral");
            orderDetail.put("sku_qty", 4);
            orderDetail.put("mrp", 33.6);
            orderDetail.put("discount_amount", 20);
            orderDetail.put("variant_id", JSONObject.NULL);
            orderDetail.put("line_item_ids", new ArrayList<>());

            pmInstance.setOrderDetails(Collections.singletonList(orderDetail));
            pmInstance.setPrescriptionLinks(Collections.singletonList("http://example.com/prescription/124"));
            pmInstance.setDiscount(0.0);
            pmInstance.setWebhookUrl("https://partner-api.dev.docpharma.in/test");

            test.info("Final payload before API call: " + pmInstance);

            // Log the final payload
            String finalPayload = pmInstance.toString(); // Assuming PayloadManager has a proper toString implementation
            test.info("Final payload before API call: " + finalPayload);

            // Make the API call
            PlaceOrderAPI api = new PlaceOrderAPI();
            api.placeOrder();

            // Log the response
            String response = pmInstance.getStatus(); // Assuming status is set in PayloadManager after API call
            test.info("Response from API: " + response);

            // Validate the response
            Assert.assertNotNull(response, "Response should not be null");
            test.pass("✅ Response validated successfully");

        } catch (Exception e) {
            test.fail("❌ Test failed with exception: " + e.getMessage());
            throw e;
        } finally {
            extent.flush();
        }
    }

    
    public void testPlaceOrderDBValidation() {
        test = extent.createTest("testPlaceOrderDBValidation", "Validate PlaceOrder data in the database");

        try {
            // Use the existing PayloadManager instance
            Assert.assertNotNull(pmInstance, "PayloadManager instance is null. Ensure testPlaceOrderAPI runs first.");

            // Connect to the database and validate data
            test.info("Connecting to the database...");
            ResultSet resultSet = DBUtil.executeQuery("SELECT * FROM partner_orders WHERE partner_order_id = '" + pmInstance.getPartnerOrderId() + "'");

            Assert.assertTrue(resultSet.next(), "No record found in the database for the given partner_order_id");

            // Validate specific fields
            Assert.assertEquals(resultSet.getString("customer_name"), pmInstance.getCustomerName(), "Customer name does not match");
            Assert.assertEquals(resultSet.getString("patient_name"), pmInstance.getPatientName(), "Patient name does not match");
            Assert.assertEquals(resultSet.getString("mobile_no"), pmInstance.getMobileNo(), "Mobile number does not match");
            Assert.assertEquals(resultSet.getString("email_id"), pmInstance.getEmailId(), "Email ID does not match");

            test.pass("✅ Database validation successful");

        } catch (SQLException e) {
            test.fail("❌ Test failed with SQL exception: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (Exception e) {
            test.fail("❌ Test failed with exception: " + e.getMessage());
            throw e;
        } finally {
            extent.flush();
        }
    }
}
