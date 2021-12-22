package com.markinaksenia.pipeline;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.tiled.AtlasTmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Main implements Screen {

  SpriteBatch batch;
  Stage stage;
  Texture img;
  TiledMap map;
  OrthographicCamera camera;
  TiledMapRenderer tiledMapRenderer;
  AtlasTmxMapLoader test;
  BitmapFont FontRed1;
  BitmapFont font1;
  Stage stage1;
  String userName = "name";
  static public Skin gameSkin;
  BitmapFont FontUser;
  BitmapFont FontRait;
  int raiting =0;
  int codeGame;
  public float flag = 0;
  public float x, x1;
  public float y, y1;
  public TextButton btnYes;
  public TextButton btnNo;
  final String FONT_CHARS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
  final String FONT_PATH = "20050.ttf";
  private Game game;
  String wordsArray[];
  FileHandle file;
  String rait;
  int a,b;

  public Main (Game aGame, int aFormal, int bFormal) {

    a=aFormal;
    b=bFormal;

    FreeTypeFontGenerator generator1 = new FreeTypeFontGenerator(Gdx.files.internal(FONT_PATH));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter1 = new  FreeTypeFontGenerator.FreeTypeFontParameter ();
    parameter1.characters = FONT_CHARS;
    parameter1.size=52;
    parameter1.color=Color.valueOf("FFA500");
    FontUser = generator1.generateFont(parameter1);
    generator1.dispose();

    FreeTypeFontGenerator generator2 = new FreeTypeFontGenerator(Gdx.files.internal(FONT_PATH));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new  FreeTypeFontGenerator.FreeTypeFontParameter ();
    parameter2.characters = FONT_CHARS;
    parameter2.size=52;
    parameter2.color=Color.GOLD;
    FontRait = generator2.generateFont(parameter2);
    generator2.dispose();

    game = aGame;
    stage = new Stage(new ScreenViewport());

    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(FONT_PATH));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    parameter.characters = FONT_CHARS;
    parameter.size = 15;
    parameter.color = Color.BLACK;

    gameSkin = new Skin(Gdx.files.internal("textButtonRussian/textButtonRus.json"));

    font1 = new BitmapFont();
    font1 = generator.generateFont(parameter);
    font1.getData().scale(1.5f);
    generator.dispose();

    batch=new SpriteBatch();
    FontRed1=new BitmapFont();
    FontRed1.setColor(Color.RED);
    FontRed1.getData().scale(1);

    float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();

    camera=new OrthographicCamera();
    camera.setToOrtho(false,w/2,h/2);
    camera.update();

    map =new TmxMapLoader().load("3.tmx");
    tiledMapRenderer=new OrthogonalTiledMapRenderer(map);

    //      Gdx.input.setInputProcessor(new GestureDetector(this));
  }

  @Override
  public void show() {
    Gdx.input.setInputProcessor(stage);
  }

  @Override
  public void render(float delta) {

    Gdx.gl.glClearColor(0,0,100,0);
    Gdx.gl.glBlendColor(GL20.GL_SRC_ALPHA,GL20.GL_ONE_MINUS_SRC_ALPHA,0,0);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



    camera.update();
    tiledMapRenderer.setView(camera.combined,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    tiledMapRenderer.render();
    stage.draw();

    batch.begin();

    boolean exists = Gdx.files.local("info.txt").exists();
    if (exists){
      file = Gdx.files.local("info.txt");
      userName = file.readString();
      wordsArray = userName.split("\\r?\\n");
      userName=wordsArray[0];
      rait=wordsArray[1];
    }

    if (flag==0 || flag==2){

      if (flag==2){
        x1=Gdx.input.getX();
        y1=Gdx.input.getY();
        flag =0;
      }

      if (flag==0){
        x = Gdx.input.getX();
        y = Gdx.input.getY();
        if (x1==x && y1== y) {

        }
        else {

          if(a!=0 && b !=0){

          if ((y <= 336168 / x || y <= 530) && x <= 780 && y <= 680 && y <= 1.59 * x + 70.84 && x >= 180 && y >= 200) {

            codeGame = 1;
            Label.LabelStyle labelStyle = new Label.LabelStyle();
            labelStyle.font = font1;
            font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            Label label = new Label("Построить трубопровод, трасса которого будет пересекать зону нормальных условий,\n" +
                    "затратить при этом минимально возможное количество вложений и закончить\n" +
                    "строительство в кратчайшие сроки.\n" +
                    "Доступный бюджет: 1.000.000 у.е.\n" +
                    "Срок строительства: 1 год", labelStyle);
            String title = "Нормальные условия";
            dialogAlert(label, title, 1);
          }

          if ((y > 336168 / x && y <= 1.09 * x + 226.24 && y >= 466 && x <= 630) || (y >= 540 && x >= 630 && y <= 1.256 * x + 78.077 && y <= 955 && x <= 805) || (y <= 940 && y >= 0.523 * x + 82.45 && x <= 971 && x >= 805 && ((x - 968) * (x - 968) + (y - 781) * (y - 781) > 34 * 34))) {
            if (Integer.parseInt(rait) < 1500) {
              Label.LabelStyle labelStyle = new Label.LabelStyle();
              labelStyle.font = font1;
              font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
              Label label = new Label("Для начала необходимо более 1500 очков рейтинга", labelStyle);
              String title = "Многолетнемерзлые грунты";
              dialogAlertError(label, title);
            } else {
              codeGame = 2;
              Label.LabelStyle labelStyle = new Label.LabelStyle();
              labelStyle.font = font1;
              font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
              Label label = new Label("Построить трубопровод, трасса которого будет пересекать зону многолетнемерзлых грунтов,\n" +
                      "затратить при этом минимально возможное количество вложений и закончить\n" +
                      "строительство в кратчайшие сроки.\n" +
                      "Доступный бюджет: 1.000.000 у.е.\n" +
                      "Срок строительства: 1 год", labelStyle);
              String title = "Многолетнемерзлые грунты";
              dialogAlert(label, title, 2);
            }

          }

          if ((y >= 250 && x > 780 && y <= 2.5 * x + 3465.09 && y <= 525 && y < 0.523 * x + 82.45 && x <= 1550) || (y < 0.523 * x + 82.45 && y <= 600 && y >= 525 && x <= 1515)) {
            if (Integer.parseInt(rait) < 3000) {
              Label.LabelStyle labelStyle = new Label.LabelStyle();
              labelStyle.font = font1;
              font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
              Label label = new Label("Для начала необходимо более 3000 очков рейтинга", labelStyle);
              String title = "Сильнопересеченная местность";
              dialogAlertError(label, title);
            } else {
              codeGame = 3;
              Label.LabelStyle labelStyle = new Label.LabelStyle();
              labelStyle.font = font1;
              font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
              Label label = new Label("Построить трубопровод, трасса которого будет пересекать зону сильнопересеченной местности,\n" +
                      "затратить при этом минимально возможное количество вложений и закончить\n" +
                      "строительство в кратчайшие сроки.\n" +
                      "Доступный бюджет: 1.000.000 у.е.\n" +
                      "Срок строительства: 1 год", labelStyle);
              String title = "Сильнопересеченная местность";
              dialogAlert(label, title, 3);
            }
          }

          if (x <= 1570 && y <= 960 && x >= 975 && y >= 600) {
              if (Integer.parseInt(rait) < 4500) {
                  Label.LabelStyle labelStyle = new Label.LabelStyle();
                  labelStyle.font = font1;
                  font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                  Label label = new Label("Для начала необходимо более 4500 очков рейтинга", labelStyle);
                  String title = "Заболоченная местность";
                  dialogAlertError(label, title);
              } else {
                  codeGame = 4;
                  Label.LabelStyle labelStyle = new Label.LabelStyle();
                  labelStyle.font = font1;
                  font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                  Label label = new Label("Построить трубопровод, трасса которого будет пересекать зону заболоченной местности,\n" +
                          "затратить при этом минимально возможное количество вложений и закончить\n" +
                          "строительство в кратчайшие сроки.\n" +
                          "Доступный бюджет: 1.000.000 у.е.\n" +
                          "Срок строительства: 1 год", labelStyle);
                  String title = "Заболоченная местность";
                  dialogAlert(label, title, 4);
              }
          }
        }
          else {
            a=1;
            b=1;
            flag=2;
          }
        }
      }

    }


    FontUser.draw(batch, userName, Gdx.graphics.getWidth()/8,Gdx.graphics.getHeight()-5*Gdx.graphics.getHeight()/22/4);
    FontRait.draw(batch, Integer.parseInt(rait)+"", 1*Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()-5*Gdx.graphics.getHeight()/22/4);

    batch.end();
    stage.draw();
  }

  @Override
  public void resize(int width, int height) {

  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void hide() {

  }

  @Override
  public void dispose() {
    stage.dispose();
  }

  int code1;
  public void dialogAlert (Label text, String title, int code){

    code1=code;
    flag = 1;
    Gdx.input.setInputProcessor(stage);
    Skin uiSkin = new Skin(Gdx.files.internal("skin/1.json"));
    final Dialog dialog = new Dialog(title, uiSkin, "default") {

      public void result(Object obj) {
        if(obj.equals(true)){
          Gdx.app.log("Log", obj.toString());
          flag=2;
          hide();
          cancel();
          remove();
          codeGame=1;
          game.setScreen(new BuildScreen(code1,game));

        }
        else {
          Gdx.app.log("Log", obj.toString());
          hide();
          cancel();
          remove();
          flag=2;
        }
      }
    };

    dialog.text(text);

    TextButton btnYes = new TextButton("Приступить", gameSkin);
    TextButton btnNo = new TextButton("Выход", gameSkin);

    dialog.button(btnYes,true);
    dialog.button(btnNo,false);

    dialog.setModal(true);
    dialog.setMovable(false);
    dialog.setResizable(false);
    dialog.show(stage);
  }

  public void dialogAlertError (Label text, String title){
    flag = 1;
    Gdx.input.setInputProcessor(stage);
    Skin uiSkin = new Skin(Gdx.files.internal("skin/1.json"));
    final Dialog dialog = new Dialog(title, uiSkin, "default") {

      public void result(Object obj) {
        if(obj.equals(true)){
          Gdx.app.log("Log", obj.toString());
          flag=2;
          hide();
          cancel();
          remove();
        }
        else {
          Gdx.app.log("Log", obj.toString());
          hide();
          cancel();
          remove();
          flag=2;
        }
      }
    };

    dialog.text(text);

    TextButton btnNo = new TextButton("Выход", gameSkin);

    dialog.button(btnNo,false);

    dialog.setModal(true);
    dialog.setMovable(false);
    dialog.setResizable(false);
    dialog.show(stage);
  }
}