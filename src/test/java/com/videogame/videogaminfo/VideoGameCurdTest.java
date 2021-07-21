package com.videogame.videogaminfo;

import com.videogame.stepsinfo.VideoGameSteps;
import com.videogame.testbase.TestBase;
import com.videogame.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.IsEqual.equalTo;


@RunWith(SerenityRunner.class)
public class VideoGameCurdTest extends TestBase {

    static int id = 1 + TestUtils.getRandomValueInt();
    static String name ="Bat Man";
    static String releaseDate = "2021-07-15T19:08:27.6092";
    static int reviewScore = 89;
    static String category = "Entertainment";
    static String rating = "Universal";

 @Steps
    VideoGameSteps videoGameSteps;
 @Title("This Test wil create new video games")
    @Test
    public void test001(){

    videoGameSteps.createNewVideoGames(id, name, releaseDate, reviewScore , category, rating).statusCode(200).log().all().extract().response()
             .body().jsonPath();

 }
 @Title("This test will get video game information by id")
    @Test
    public void test002(){
     videoGameSteps.getVideoGameById(id).statusCode(200).log().all();
 }

 @Title("This test will update the video game by existing id")
    @Test
    public void test003(){
     id = id ;
     name = name + "_Updated";
     releaseDate = releaseDate ;
     reviewScore =  reviewScore + 1  ;
     category = category + "_Updated";
     rating = rating + "_Updated";

     videoGameSteps.updateVideoGameById(id,name,releaseDate,reviewScore,category,rating).statusCode(200).log().all();
     videoGameSteps.getVideoGameById(id).body("id",equalTo(id));

 }
@Title("This test will delete the videogame by id")
    @Test
    public void test004(){
    videoGameSteps.deleteVideoGame(id).statusCode(200).log().all();
  //videoGameSteps.getVideoGameById(id).statusCode(404).log().all();

}

@Title("This test will get videogame")
    @Test
    public void test005(){
    videoGameSteps.getAllVideoGameFromList().log().all().statusCode(200);
}
}
