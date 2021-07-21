package com.videogame.stepsinfo;

import com.videogame.constants.EndPoints;
import com.videogame.model.VideoGamePojo;
import io.restassured.http.ContentType;


import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class VideoGameSteps {

    @Step("Creating New Video Games with Id: {0}, name:{1}, releaseDate:{2}, reviewScore:{3}, category:{4},rating:{5}")
    public ValidatableResponse createNewVideoGames(int id, String name, String releaseDate, int reviewScore, String category,
                                                   String rating) {

        VideoGamePojo videoGamePojo = new VideoGamePojo();

        videoGamePojo.setId(id);
        videoGamePojo.setName(name);
        videoGamePojo.setReleaseDate(releaseDate);
        videoGamePojo.setReviewScore(reviewScore);
        videoGamePojo.setCategory(category);
        videoGamePojo.setRating(rating);

        return SerenityRest.rest().given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(videoGamePojo).accept("application/json")
                .post(EndPoints.CREATE_NEW_VIDEOGAME)
                .then();

    }
@Step("Getting the Video Game information created by id")
public ValidatableResponse getVideoGameById(int videogameid){
    return SerenityRest.rest()
            .given().accept("application/json")
            .pathParam("id",videogameid).accept("application/json")
            .log().all()
            .when()
            .get(EndPoints.GET_SINGLE_VIDEOGAME_BY_ID)
            .then();

}
    @Step("Updating Video Games with Id: {0}, name:{1}, releaseDate:{2}, reviewScore:{3}, category:{4},rating:{5}")
    public ValidatableResponse updateVideoGameById( int id ,String name, String releaseDate, int reviewScore, String category,
                                                   String rating) {

        VideoGamePojo videoGamePojo = new VideoGamePojo();

        videoGamePojo.setId(id);
        videoGamePojo.setName(name);
        videoGamePojo.setReleaseDate(releaseDate);
        videoGamePojo.setReviewScore(reviewScore);
        videoGamePojo.setCategory(category);
        videoGamePojo.setRating(rating);

        return SerenityRest.rest()
                .given()
                .pathParam("id",id).accept("application/json")
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(videoGamePojo).accept("application/json")
                .put(EndPoints.UPDATE_VIDEOGAME_BY_ID)
                .then();

    }
    @Step("Deleting the videogame with Id ")
    public ValidatableResponse deleteVideoGame(int videogameid) {
        return SerenityRest.rest()
                .given()//.accept("application/json")
                .pathParam("id", videogameid).accept("application/json")
                .when()
                .delete(EndPoints.DELETE_VIDEOGAME_BY_ID)
                .then();
    }
@Step("Getting the all video game from data")
    public ValidatableResponse getAllVideoGameFromList(){
        return  SerenityRest.rest()
                .given().accept("application/json")
                .when()
                .get(EndPoints.GET_ALL_VIDEOGAME)
                .then();

}
}