package api;



import core.PayloadManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlaceOrderAPI {

    public void placeOrder() {
        PayloadManager pm = PayloadManager.getInstance();

        JSONObject payload = new JSONObject();
        payload.put("partner_order_id", pm.getPartnerOrderId());
        payload.put("partner_order_no", pm.getPartnerOrderNo());
        payload.put("customer_name", pm.getCustomerName());
        payload.put("patient_name", pm.getPatientName());
        payload.put("mobile_no", pm.getMobileNo());
        payload.put("email_id", pm.getEmailId());
        payload.put("state", pm.getState());
        payload.put("city", pm.getCity());
        payload.put("zipcode", pm.getZipcode());
        payload.put("lat", pm.getLat());
        payload.put("long", pm.getLon());
        payload.put("address_1", pm.getAddress1());
        payload.put("address_2", pm.getAddress2());
        payload.put("payment_mode_order", pm.getPaymentModeOrder());
        payload.put("payment_mode_item", new JSONObject(pm.getPaymentModeItem()));
        payload.put("order_type", pm.getOrderType());
        payload.put("collectible", pm.getCollectible());
        payload.put("shipping_charges", pm.getShippingCharges());
        payload.put("order_details", new JSONArray(pm.getOrderDetails()));
        payload.put("prescription_links", new JSONArray(pm.getPrescriptionLinks()));
        payload.put("udf1", JSONObject.NULL);
        payload.put("udf2", JSONObject.NULL);
        payload.put("udf3", JSONObject.NULL);
        payload.put("discount", pm.getDiscount());
        payload.put("webhook_url", pm.getWebhookUrl());

        Response response = RestAssured
                .given()
                .header("x-api-key", "4b08ae68-c54d-43df-abb2-026611f9db54-f2903bffbe97e45c")
                .header("Content-Type", "application/json")
                .body(payload.toString())
                .post("https://partner-api.dev.docpharma.in/v2/place-order/");

        String responseString = response.asString();

        // Print the full response received
        System.out.println("Full API Response: " + responseString);

        // Validate response before parsing
        if (!responseString.trim().startsWith("{")) {
            throw new JSONException("Invalid response format: " + responseString);
        }

        JSONObject resJson = new JSONObject(responseString);

        // Set response values
        pm.setStatus(resJson.optString("status", "Unknown"));
        if (resJson.has("status_code")) {
            pm.setStatusCode(resJson.getString("status_code"));
        } else {
            System.err.println("Warning: 'status_code' not found in response.");
            pm.setStatusCode("Unknown");
        }
        pm.setOrderNumber(resJson.optString("order_number", "Unknown"));

        if (resJson.has("data")) {
            JSONArray dataArray = resJson.getJSONArray("data");

            // Convert JSONArray to List<Map<String, Object>>
            List<Map<String, Object>> dataList = new ArrayList<>();
            for (Object obj : dataArray) {
                if (obj instanceof Map) {
                    dataList.add((Map<String, Object>) obj);
                }
            }
            pm.setData(dataList);

            if (!dataArray.isEmpty()) {
                JSONObject firstDataEntry = dataArray.getJSONObject(0);
                pm.setResponseId(firstDataEntry.optInt("id", -1));
                pm.setPharmacyId(firstDataEntry.optString("pharmacy_id", "Unknown"));
                pm.setPharmacyName(firstDataEntry.optString("pharmacy_name", "Unknown"));
                pm.setErpId(firstDataEntry.optInt("erp_id", -1));
                pm.setErpApiKey(firstDataEntry.optString("erp_api_key", "Unknown"));
                pm.setResponseLat(firstDataEntry.optDouble("lat", 0.0));
                pm.setResponseLong(firstDataEntry.optDouble("long", 0.0));
                pm.setDisabled(firstDataEntry.optBoolean("is_disabled", false));
                pm.setDistance(firstDataEntry.optDouble("distance", 0.0));
            }
        } else {
            System.err.println("Warning: 'data' array not found in response.");
        }
    }
}
