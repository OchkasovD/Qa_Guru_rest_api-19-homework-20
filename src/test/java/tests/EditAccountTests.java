package tests;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static helpers.AuthApi.authCookieKey;
import static io.restassured.RestAssured.given;

public class EditAccountTests extends TestBase {
    @Test
    void editUserProfileTest() {
        String valueId = "3147862",
                valueFirstName = "Rulon",
                valueLastName = "Oboev",
                valueEmail = "test@test.com",
                valueCompany = "Test",
                valueCountryId = "50",
                valueStateProvinceId = "0",
                valueCity = "Moscow",
                valueAddress1 = "Chertanovo",
                valueAddress2 = "Severnoe",
                valueZipPostalCode = "140250",
                valuePhoneNumber = "49534340053",
                valueFaxNumber = "49534340053";


        String authCookieValue = authApi.getAuthCookie(login, password);

        given()
                .contentType("application/x-www-form-urlencoded")
                .cookie(authCookieKey, authCookieValue)
                .formParam("Address.Id", valueId)
                .formParam("Address.FirstName", valueFirstName)
                .formParam("Address.LastName", valueLastName)
                .formParam("Address.Email", valueEmail)
                .formParam("Address.Company", valueCompany)
                .formParam("Address.CountryId", valueCountryId)
                .formParam("Address.StateProvinceId", valueStateProvinceId)
                .formParam("Address.City", valueCity)
                .formParam("Address.Address1", valueAddress1)
                .formParam("Address.Address2", valueAddress2)
                .formParam("Address.ZipPostalCode", valueZipPostalCode)
                .formParam("Address.PhoneNumber", valuePhoneNumber)
                .formParam("Address.FaxNumber", valueFaxNumber)
                .when()
                .post("/customer/addressedit/3147862")
                .then()
                .log().all()
                .assertThat()
                .statusCode(302);


        step("Check edited customer title", () ->
                open("/login"));
        $("#Email").setValue(login);
        $("#Password").setValue(password).pressEnter();
        step("Check edited customer title", () ->
                open("/login"));
        open("/customer/addresses");
        $(".info").shouldHave(text("Rulon Oboev"));
    }

    @Test
    void editAddressTest() {
        Map<String, String> data = new HashMap<>();
        data.put("Address.Id", "3147862");
        data.put("Address.FirstName", "Ivan");
        data.put("Address.LastName", "Ivanov");
        data.put("Address.Email", "test@gmail.com");
        data.put("Address.Company", "company");
        data.put("Address.CountryId", "2");
        data.put("Address.StateProvinceId", "63");
        data.put("Address.City", "city");
        data.put("Address.Address1", "address1");
        data.put("Address.Address2", "address2");
        data.put("Address.ZipPostalCode", "123456");
        data.put("Address.PhoneNumber", "098987876");
        data.put("Address.FaxNumber", "112233");

        String authCookieValue = authApi.getAuthCookie(login, password);

        given()
                .contentType("application/x-www-form-urlencoded")
                .cookie(authCookieKey, authCookieValue)
                .formParams(data)
                .when()
                .post("/customer/addressedit/3147862")
                .then()
                .log().all()
                .statusCode(302);

        step("Check edited customer title", () ->
                open("/login"));
        $("#Email").setValue(login);
        $("#Password").setValue(password).pressEnter();
        step("Check edited customer title", () ->
                open("/login"));
        open("/customer/addresses");
        $(".info").shouldHave(text("Ivan Ivanov"));


    }
}





