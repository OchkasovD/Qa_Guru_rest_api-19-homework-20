package tests;
import org.junit.jupiter.api.Test;
import static helpers.AuthApi.authCookieKey;
import static io.restassured.RestAssured.given;

public class EditAccountTest extends TestBase {
    @Test
    void editUserProfileTest() {
        String valueId = "3116110",
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
                .post("/customer/addressedit/3116110")
                .then()
                .log().all()
                .assertThat()
                .statusCode(302);
    }
}