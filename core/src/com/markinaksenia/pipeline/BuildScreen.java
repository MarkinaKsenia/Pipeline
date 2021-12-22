package com.markinaksenia.pipeline;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class BuildScreen implements Screen {

    Stage stage;
    SpriteBatch batch;
    BitmapFont FontRed1;
    BitmapFont FontTimer;
    BitmapFont FontCoin;
    Texture textureCoin;
    Table table;
    Table container;
    Texture textureBlue;
    Image plosh;
    Image blue;
    ScrollPane pane;
    TextButton preparatoryButton;
    TextButton wrapUpButton;
    TextButton mainWorkButton;
    FileHandle file;
    String textFile,rait;

    double raitItog;
    int result=0;

    int raiting;
    long sec;

    Table containerWrap;
    ScrollPane paneWrap;
    Table korzinaWrap;

    Table containerMain;
    ScrollPane paneMain;
    Table korzinaMain;

    static public Skin gameSkin;
    public float flag = 0;
    Table korzina;
    TextButton returnMainWork;
    final Skin uiSkin;
    boolean hasBeenClick=false;  boolean hasBeenClick1=false; boolean hasBeenClick2=false; boolean hasBeenClick3=false; boolean hasBeenClick4=false; boolean hasBeenClick5=false; boolean hasBeenClick6=false;
    boolean hasBeenClick7=false, hasBeenClick8=false, hasBeenClick9=false, hasBeenClick10=false;
    ScrollPane scrollPane;
    Table preparatoryWorkTable;
    int yes1=0, yes2=0, yes3=0, yes4=0, yes5=0, yes6=0, yes7=0;
    int yes8=0, yes9=0, yes10=0, yes11=0;
    int yes12=0, yes13=0, yes14=0, yes15=0, yes16=0, yes17=0, yes18=0, yes19=0, yes20=0, yes21=0, yes22=0, yes23=0;
    int yes1Osn=0, yes2Osn=0, yes3Osn=0;
    public String name;
    String wordsArray[];
    private Game game;
    BitmapFont font1;
    BitmapFont font2;
    Texture texture;
    TiledMap map;
    TiledMap map2;
    Table buidTownButtonInventory;
    int right =0, right1=0, right2=0, right3=0, right4=0, right5=0, right6=0; //для выбора инструментов в работах
    int right7 =0, right8=0, right9=0, right10=0,  right11=0; //для выбора инструментов в работах
    int right12 =0, right13=0, right14=0, right15=0,  right16=0,right17=0, right18=0, right19=0, right20=0, right21=0, right22=0;
    public double coin = 1000000;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    TiledMapRenderer tiledMapRenderer2;
    final String FONT_CHARS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
    final String FONT_PATH = "20050.ttf";
    public double timeSeconds = 0f;
    public float flagWork1 =0, flagWork2=0, flagWork3=0;
    public String [] rightLogicOsn = { "Подготовительные работы", "Погрузочно-разгрузочные работы", "Основные работы"};
    public String [] rightLogicPreparatory = { "Размещение строительного \n городка",
          "Строительство подъездных \n дорог",
            "Подготовка площадок \n для строительства и склада \n материалов",
            "Геодезическая подготовка \n трассы и обозначение границ \n строительной полосы",
            "Валка деревьев, срезка \n кустарников и вывоз древесины",
            "Планировка строительной \n трассы",
            "Устройство вдольтрассовых \n проездов"};
    public String [] rightwrapUp = { "Приемка труб на \n станции разгрузки",
            "Доставка труб на \n площадки складирования",
            "Складирование труб и \n строительных материалов",
            "Развозка строительных \n материалов со склада \n вдоль строящейся трассы"};
    public String [] rightmainWork = { "Срезка плодородного \n слоя почвы",
            "Разработка траншеи",
            "Подготовка дна траншеи \n присыпкой мягким грунтом",
            "Подготовка труб \n к сборке",
            "Сборка и \n сварка труб",
            "Контроль сварных \n соединений",
            "Изоляция сварных \n стыков",
            "Укладка трубопровода в \n траншею",
            "Засыпка уложенного \n трубопровода",
            "Очистка полости  \n промывкой/продувкой с \n пропуском отчистных поршней",
            "Гидравлическое или  \n пневматическое испытание \n трубопровода",
            "Сооружение средств \n электрохимической защиты"};
    public int currentButton = 0;
    public int endPreparatory=0, endWrap=0, endMain=0;
    public int currentButtonwrap = 0; public int currentButtonOsn = 0; public int currentButtonMain= 0;

    //Кнопки подготовительных работ

    final TextButton buidTownButton;
    TextButton roudButton;
    TextButton ploshadkaButton;
    TextButton geoButton;
    TextButton treeButton;
    TextButton planButton;
    TextButton ustrButton;
    TextButton infBuild, infPlosh, infRoud, infGeo, infTree, infPlan, infUstr;

    //Кнопки погрузочно-разгрузочных и транспортных работ
    TextButton priemka;
    TextButton dostavka;
    TextButton skladirovanie;
    TextButton razvozka;
    TextButton infPriemka, infDostavka, infSkladirovanie, infRazvozka;

    Image timerImg;
    Image coinImg;

    int close=0;

    //Кнопки основного цикла
    TextButton srezka;
    TextButton razrabotka;
    TextButton podgotovka;
    TextButton podgotovka_trub;
    TextButton sborka;
    TextButton control;
    TextButton izolyation;
    TextButton ukladka;
    TextButton zasypka;
    TextButton btnClose;
    TextButton otchistka;
    TextButton gidravlika;
    TextButton sooryzhenie;
    TextButton infSrezka, infRazrabotka, infPodgotovka, infPodgotovka_trub,infSborka, infControl, infIzolyation, infUkladka, infZasypka, infOtchistka, infGidravlika, infSooryzhenie;

    public BuildScreen (int a, Game aGame) {

        game = aGame;
        batch=new SpriteBatch();
        FontRed1=new BitmapFont();
        FontRed1.setColor(Color.RED);
        FontRed1.getData().scale(1);

        FontTimer=new BitmapFont();
        FontTimer.setColor(Color.OLIVE);
        FontTimer.getData().scale(3);

        FontCoin=new BitmapFont();
        FontCoin.setColor(Color.GOLD);
        FontCoin.getData().scale(3);

        stage = new Stage(new ScreenViewport());

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();



        uiSkin = new Skin(Gdx.files.internal("textButtonRussian/textButtonRus.json"));

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(FONT_PATH));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = FONT_CHARS;
        parameter.size = 15;
        parameter.color = Color.BLACK;

        FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter2.characters = FONT_CHARS;
        parameter2.size = 12;
        parameter2.color = Color.BLACK;

        font1 = new BitmapFont();
        font1 = generator.generateFont(parameter);
        font1.getData().scale(1.5f);

        font2 = new BitmapFont();
        font2 = generator.generateFont(parameter);
        font2.getData().scale(1.2f);
        generator.dispose();

        camera=new OrthographicCamera();
        camera.setToOrtho(false,w/2,h/2);
        camera.update();

        texture = new Texture(Gdx.files.internal("Player/hourglass.png"));
        textureCoin = new Texture(Gdx.files.internal("Player/dollar.png"));

        TextButton backButton = new TextButton("Выйти",uiSkin);
        backButton.setHeight(Gdx.graphics.getHeight()/12);
        backButton.setWidth(Gdx.graphics.getWidth()/6);
        backButton.setPosition(Gdx.graphics.getWidth()-11*backButton.getWidth()/10,Gdx.graphics.getHeight()-5*backButton.getHeight()/4);

         preparatoryButton = new TextButton("Подготовительные работы",uiSkin);
        preparatoryButton.setHeight(Gdx.graphics.getHeight()/2);
        preparatoryButton.setWidth(Gdx.graphics.getWidth()/6);

        wrapUpButton = new TextButton("Погрузочно-разгрузочные работы",uiSkin);
        wrapUpButton.setHeight(Gdx.graphics.getHeight()/2);
        wrapUpButton.setWidth(Gdx.graphics.getWidth()/6);

        mainWorkButton = new TextButton("Основные работы",uiSkin);
        mainWorkButton.setHeight(Gdx.graphics.getHeight()/12);
        mainWorkButton.setWidth(Gdx.graphics.getWidth()/6);

        table = new Table();
        table.add(preparatoryButton).width(10*Gdx.graphics.getWidth()/32).height(Gdx.graphics.getHeight()/8);
        table.add(wrapUpButton).width(10*Gdx.graphics.getWidth()/32).height(Gdx.graphics.getHeight()/8);
        table.add(mainWorkButton).width(10*Gdx.graphics.getWidth()/32).height(Gdx.graphics.getHeight()/8);
        table.setFillParent(true);
        table.setPosition(0,-430);

        buidTownButtonInventory = new Table();

        //Подготовительные

        buidTownButton = new TextButton("Размещение строительного \n городка",uiSkin);
        roudButton = new TextButton("Строительство подъездных \n дорог",uiSkin);
        ploshadkaButton = new TextButton("Подготовка площадок \n для строительства и склада \n материалов",uiSkin);
        geoButton = new TextButton("Геодезическая подготовка \n трассы и обозначение границ \n строительной полосы",uiSkin);
        treeButton = new TextButton("Валка деревьев, срезка \n кустарников и вывоз древесины",uiSkin);
        planButton = new TextButton("Планировка строительной \n трассы",uiSkin);
        ustrButton = new TextButton("Устройство вдольтрассовых \n проездов",uiSkin);

        infBuild = new TextButton(" ! ",uiSkin);
        infRoud = new TextButton(" ! ",uiSkin);
        infPlosh = new TextButton(" ! ",uiSkin);
        infGeo = new TextButton(" ! ",uiSkin);
        infTree = new TextButton(" ! ",uiSkin);
        infPlan = new TextButton(" ! ",uiSkin);
        infUstr = new TextButton(" ! ",uiSkin);


        //Погрузочно-разгрузочные

        priemka = new TextButton("Приемка труб на \n станции разгрузки",uiSkin);
        dostavka = new TextButton("Доставка труб на \n площадки складирования",uiSkin);
        skladirovanie = new TextButton("Складирование труб и \n строительных материалов",uiSkin);
        razvozka = new TextButton("Развозка строительных \n материалов со склада \n вдоль строящейся трассы",uiSkin);

        infPriemka = new TextButton(" ! ",uiSkin);
        infDostavka = new TextButton(" ! ",uiSkin);
        infSkladirovanie = new TextButton(" ! ",uiSkin);
        infRazvozka = new TextButton(" ! ",uiSkin);

        //Работы основного цикла
        srezka= new TextButton("Срезка плодородного \n слоя почвы",uiSkin);
        razrabotka = new TextButton("Разработка траншеи",uiSkin);
        podgotovka = new TextButton("Подготовка дна траншеи \n присыпкой мягким грунтом",uiSkin);
        podgotovka_trub = new TextButton("Подготовка труб \n к сборке",uiSkin);
        sborka = new TextButton("Сборка и \n сварка труб",uiSkin);
        control = new TextButton("Контроль сварных \n соединений",uiSkin);
        izolyation = new TextButton("Изоляция сварных \n стыков",uiSkin);
        ukladka = new TextButton("Укладка трубопровода в \n траншею",uiSkin);
        zasypka= new TextButton("Засыпка уложенного \n трубопровода",uiSkin);
        otchistka = new TextButton("Очистка полости  \n промывкой/продувкой с \n пропуском отчистных поршней",uiSkin);
        gidravlika = new TextButton("Гидравлическое или  \n пневматическое испытание \n трубопровода",uiSkin);
        sooryzhenie= new TextButton("Сооружение средств \n электрохимической защиты",uiSkin);

        infSrezka = new TextButton(" ! ",uiSkin);
        infRazrabotka = new TextButton(" ! ",uiSkin);
        infPodgotovka = new TextButton(" ! ",uiSkin);
        infPodgotovka_trub = new TextButton(" ! ",uiSkin);
        infSborka = new TextButton(" ! ",uiSkin);
        infControl = new TextButton(" ! ",uiSkin);
        infIzolyation = new TextButton(" ! ",uiSkin);
        infUkladka = new TextButton(" ! ",uiSkin);
        infZasypka = new TextButton(" ! ",uiSkin);
        infOtchistka = new TextButton(" ! ",uiSkin);
        infGidravlika = new TextButton(" ! ",uiSkin);
        infSooryzhenie = new TextButton(" ! ",uiSkin);

        btnClose = new TextButton(" x ", uiSkin);

        returnMainWork = new TextButton("Основной цикл \n работ ",uiSkin);
        returnMainWork.setHeight(Gdx.graphics.getHeight()/12);
        returnMainWork.setWidth(Gdx.graphics.getWidth()/6);
        returnMainWork.setPosition(Gdx.graphics.getWidth()-11*backButton.getWidth()/10,Gdx.graphics.getHeight()-5*backButton.getHeight()/4-backButton.getHeight()-10);

        returnMainWork.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
              if (buidTownButtonInventory.getStage() != null){
                    buidTownButtonInventory.remove();
                }
                if (korzina.getStage() != null){
                    korzina.remove();
                }
                if (korzinaWrap.getStage() != null){
                    korzinaWrap.remove();
                }
                if (korzinaMain.getStage() != null){
                    korzinaMain.remove();
                }
                if (table.getStage() == null){
                    stage.addActor(table);
                }

            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        stage.addActor(returnMainWork);

        container = new Table();
        container.setFillParent(true);
        container.setPosition(0,-410);

        Table content = new Table();
        content.defaults().height(210);
        content.defaults().width(660);
        content.add(infRoud).width(30).height(30);
        content.add(infBuild).width(30).height(30);
        content.add(infGeo).width(30).height(30);
        content.add(infPlosh).width(30).height(30);
        content.add(infTree).width(30).height(30);
        content.add(infUstr).width(30).height(30);
        content.add(infPlan).width(30).height(30);
        content.row();
        content.add(roudButton).width(10*Gdx.graphics.getWidth()/32).height(Gdx.graphics.getHeight()/8);
        content.add(buidTownButton).width(10*Gdx.graphics.getWidth()/32).height(Gdx.graphics.getHeight()/8);
        content.add(geoButton).width(10*Gdx.graphics.getWidth()/32).height(Gdx.graphics.getHeight()/8);
        content.add(ploshadkaButton).width(10*Gdx.graphics.getWidth()/32).height(Gdx.graphics.getHeight()/8);
        content.add(treeButton).width(10*Gdx.graphics.getWidth()/32).height(Gdx.graphics.getHeight()/8);
        content.add(ustrButton).width(10*Gdx.graphics.getWidth()/32).height(Gdx.graphics.getHeight()/8);
        content.add(planButton).width(10*Gdx.graphics.getWidth()/32).height(Gdx.graphics.getHeight()/8);

        pane = new ScrollPane(content);
        pane.layout();
        pane.setWidth(1800);
        pane.setHeight(210);

        korzina = new Table();
        korzina.addActor(pane);
        korzina.setY(35);
        korzina.setX(60);

        containerWrap = new Table();
        containerWrap.setFillParent(true);
        containerWrap.setPosition(0,-410);

        Table contentWrap = new Table();
        contentWrap.defaults().height(210);
        contentWrap.defaults().width(660);
        contentWrap.add(infSkladirovanie).width(30).height(30);
        contentWrap.add(infDostavka).width(30).height(30);
        contentWrap.add(infPriemka).width(30).height(30);
        contentWrap.add(infRazvozka).width(30).height(30);
        contentWrap.row();
        contentWrap.add(skladirovanie ).width(10*Gdx.graphics.getWidth()/32).height(Gdx.graphics.getHeight()/8);
        contentWrap.add(dostavka).width(10*Gdx.graphics.getWidth()/32).height(Gdx.graphics.getHeight()/8);
        contentWrap.add(priemka).width(10*Gdx.graphics.getWidth()/32).height(Gdx.graphics.getHeight()/8);
        contentWrap.add(razvozka).width(10*Gdx.graphics.getWidth()/32).height(Gdx.graphics.getHeight()/8);

        paneWrap = new ScrollPane(contentWrap);
        paneWrap.layout();
        paneWrap.setWidth(1800);
        paneWrap.setHeight(210);

        korzinaWrap = new Table();
        korzinaWrap.addActor(paneWrap);
        korzinaWrap.setY(35);
        korzinaWrap.setX(60);

        containerMain = new Table();
        containerMain.setFillParent(true);
        containerMain.setPosition(0,-410);

        Table contentMain = new Table();
        contentMain.defaults().height(210);
        contentMain.defaults().width(660);
        contentMain.add(infControl).width(30).height(30);
        contentMain.add(infZasypka).width(30).height(30);
        contentMain.add(infSborka).width(30).height(30);
        contentMain.add(infUkladka).width(30).height(30);
        contentMain.add(infIzolyation).width(30).height(30);
        contentMain.add(infGidravlika).width(30).height(30);
        contentMain.add(infSrezka).width(30).height(30);
        contentMain.add(infRazrabotka).width(30).height(30);
        contentMain.add(infPodgotovka_trub).width(30).height(30);
        contentMain.add(infOtchistka).width(30).height(30);
        contentMain.add(infPodgotovka).width(30).height(30);
        contentMain.add(infSooryzhenie).width(30).height(30);
        contentMain.row();
        contentMain.add(control).width(10*Gdx.graphics.getWidth()/32).height(Gdx.graphics.getHeight()/8);
        contentMain.add(zasypka).width(10*Gdx.graphics.getWidth()/32).height(Gdx.graphics.getHeight()/8);
        contentMain.add(sborka).width(10*Gdx.graphics.getWidth()/32).height(Gdx.graphics.getHeight()/8);
        contentMain.add(ukladka).width(10*Gdx.graphics.getWidth()/32).height(Gdx.graphics.getHeight()/8);
        contentMain.add(izolyation).width(10*Gdx.graphics.getWidth()/32).height(Gdx.graphics.getHeight()/8);
        contentMain.add(gidravlika).width(10*Gdx.graphics.getWidth()/32).height(Gdx.graphics.getHeight()/8);
        contentMain.add(srezka).width(10*Gdx.graphics.getWidth()/32).height(Gdx.graphics.getHeight()/8);
        contentMain.add(razrabotka).width(10*Gdx.graphics.getWidth()/32).height(Gdx.graphics.getHeight()/8);
        contentMain.add(podgotovka_trub).width(10*Gdx.graphics.getWidth()/32).height(Gdx.graphics.getHeight()/8);
        contentMain.add(otchistka).width(10*Gdx.graphics.getWidth()/32).height(Gdx.graphics.getHeight()/8);
        contentMain.add(podgotovka).width(10*Gdx.graphics.getWidth()/32).height(Gdx.graphics.getHeight()/8);
        contentMain.add(sooryzhenie).width(10*Gdx.graphics.getWidth()/32).height(Gdx.graphics.getHeight()/8);

        timerImg = new Image(texture);
        timerImg.setSize(texture.getWidth()/5, texture.getHeight()/5);
        timerImg.setPosition(Gdx.graphics.getWidth()-21*timerImg.getWidth()/2,Gdx.graphics.getHeight()-12*timerImg.getHeight()/10);

        paneMain = new ScrollPane(contentMain);
        paneMain.layout();
        paneMain.setWidth(1800);
        paneMain.setHeight(210);

        korzinaMain = new Table();
        korzinaMain.addActor(paneMain);
        korzinaMain.setY(35);
        korzinaMain.setX(60);




        infPriemka.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {

                return true;
            }

        });

        infBuild.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                Label.LabelStyle labelStyle = new Label.LabelStyle();
                labelStyle.font = font1;
                font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                Label label = new Label("Строительный городок необходим для проживания в нем рабочих на период строительства, \n" +
                        "состоит из жилых вагончиков, столовой, бани, штаба руководителей стрйоки. \n" +
                        "Как правило, временный городок размещается вблизи к населенным пунктам, \n" +
                        "либо к существующим автодорогам", labelStyle);
                dialogAlert(label, "Размещение строительного городка");
                return true;
            }

        });

        infRoud.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                Label.LabelStyle labelStyle = new Label.LabelStyle();
                labelStyle.font = font1;
                font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                Label label = new Label("Подъездные дороги необходимы для соединения пунктов приема грузов \n с производственной базой и трассой трубопровода. ", labelStyle);
                dialogAlert(label, "Строительство подъездных дорог");
                return true;
            }
        });


        infPlosh.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                Label.LabelStyle labelStyle = new Label.LabelStyle();
                labelStyle.font = font1;
                font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                Label label = new Label("Производственная база служит для размещения ремонтно-технической мастерской, \n стоянки автотранспорта, складирования строительных материалов. ", labelStyle);
                dialogAlert(label, "Подготовка площадок для строительной площадки и склада материалов");
                return true;
            }
        });

        infGeo.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                Label.LabelStyle labelStyle = new Label.LabelStyle();
                labelStyle.font = font1;
                font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                Label label = new Label("Определение на местности фактических границ, в которых будет организовано строительство трубопровода: \n " +
                        "обозначаются места пересечения с препятствиями и коммуникациями, \n повороты в горизонтальной плоскости." +
                        " Необходимо окрасить деревья, \n не подлежащие спиливанию, тем самым обозначив границы строительной полосы.", labelStyle);
                dialogAlert(label, "Геодезическая подготовка трассы и обозначение границ строительной полосы");
                return true;
            }
        });

        infTree.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                Label.LabelStyle labelStyle = new Label.LabelStyle();
                labelStyle.font = font1;
                font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                Label label = new Label("Подлежащие спиливанию деревья и кустарники удаляются с расчищаемой полосы. \n " +
                        "Также выполняется корчевка пней и вывоз древесины. \n" +
                        " Вырубленный  лес удаляется со строительной полосы и транспортируется для складирования \n" +
                        " на специальных площадках, на отведенных для этих целей землях.", labelStyle);
                dialogAlert(label, "Валка деревьев, срезка кустарника и вывоз древесины");
                return true;
            }
        });

        infPlan.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                Label.LabelStyle labelStyle = new Label.LabelStyle();
                labelStyle.font = font1;
                font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                Label label = new Label("Планировка строительной полосы необходима для выравнивания рельефа, \n" +
                        " придания ему формы, необходимой для проведения определенных видов строительных работ. \n" +
                        " Выполняется по всей ширине полосы отвода, на некоторых участках грунт срезают, на некоторых – досыпают.", labelStyle);
                dialogAlert(label, "Планировка строительной трассы");
                return true;
            }
        });

        infUstr.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                Label.LabelStyle labelStyle = new Label.LabelStyle();
                labelStyle.font = font1;
                font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                Label label = new Label("Строительство вдольтрассового проезда необходимо для движения строительной техники, \n" +
                        " автомобильного транспорта, для доставки грузов, материалов и рабочих, \n" +
                        " а также для обслуживания трубопровода в процессе эксплуатации. ", labelStyle);
                dialogAlert(label, "Устройство вдольтрассовых проездов");
                return true;
            }
        });

        // Информация о погрузочно-разгрузочных
        infPriemka.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                Label.LabelStyle labelStyle = new Label.LabelStyle();
                labelStyle.font = font1;
                font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                Label label = new Label("Этот этап включает в себя выгрузку труб из \n" +
                        "железнодорожных вагонов на прирельсовых складах", labelStyle);
                dialogAlert(label, "Приемка труб на станции разгрузки");
                return true;
            }
        });

        infDostavka.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                Label.LabelStyle labelStyle = new Label.LabelStyle();
                labelStyle.font = font1;
                font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                Label label = new Label("Трубы и другие строительные материалы транспортируются \n" +
                        "со станции разгрузки на трубосварочные базы или в места складирования.", labelStyle);
                dialogAlert(label, "\n Доставка труб на площадки складирования \n ");
                return true;
            }
        });

        infSkladirovanie.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                Label.LabelStyle labelStyle = new Label.LabelStyle();
                labelStyle.font = font1;
                font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                Label label = new Label("Временное складирование труб осуществляется до начала \n" +
                        " их использования на трассе вблизи строительного городка \n" +
                        "на территории производственной базы. \n" +
                        "Количество труб на складе определяется темпом строительства, \n" +
                        "во избежание срывов сроков.", labelStyle);
                dialogAlert(label, "Складирование труб и строительных материалов.");
                return true;
            }
        });

        infRazvozka.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                Label.LabelStyle labelStyle = new Label.LabelStyle();
                labelStyle.font = font1;
                font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                Label label = new Label("Перемещение поступивших на склад материалов и их размещение \n" +
                        "вдоль трассы трубопровода. Производится поэтапно в соответствии \n" +
                        "с календарным графиком использования строительных материалов.", labelStyle);
                dialogAlert(label, "Развозка строительных материалов со склада вдоль строящейся трассы.");
                return true;
            }
        });

        infControl.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                Label.LabelStyle labelStyle = new Label.LabelStyle();
                labelStyle.font = font1;
                font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                Label label = new Label("Контроль необходим для определения дефектов в полученных сварных соединениях. \n" +
                        " На основании контроля сварной стык можно признать бракованным, либо отдать на ремонт, \n" +
                        "либо признать годным и разрешить производство дальнейших работ. \n", labelStyle);
                dialogAlert(label, "Контроль сварных соединений");
                return true;
            }
        });

        infZasypka.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                Label.LabelStyle labelStyle = new Label.LabelStyle();
                labelStyle.font = font1;
                font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                Label label = new Label("Для обеспечения стабильных условий работы трубопровода, все трубопроводы подлежат засыпке. \n" +
                        "При засыпке трубопровода сначала используют минеральный грунт, \n" +
                        "затем потенциально-плодородный грунт и затем плодородную почву.", labelStyle);
                dialogAlert(label, "Засыпка уложенного трубопровода");
                return true;
            }
        });

        infSborka.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                Label.LabelStyle labelStyle = new Label.LabelStyle();
                labelStyle.font = font1;
                font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                Label label = new Label("Для того, чтобы элементы трубопроводов располагались идеально ровно, их фиксируют. \n" +
                        "А для соединения их в непрерывную нить – сваривают.", labelStyle);
                dialogAlert(label, "Сборка и сварка труб");
                return true;
            }
        });

        infUkladka.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                Label.LabelStyle labelStyle = new Label.LabelStyle();
                labelStyle.font = font1;
                font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                Label label = new Label("Производится укладка сваренного и заизолированного трубопровода \n" +
                        "в заранее подготовленную траншею.", labelStyle);
                dialogAlert(label, "Укладка трубопровода в траншею");
                return true;
            }
        });

        infIzolyation.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                Label.LabelStyle labelStyle = new Label.LabelStyle();
                labelStyle.font = font1;
                font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                Label label = new Label("Подземные и наземные участки трубопроводов \n" +
                        "должны быть максимально защищены от любых видов воздействий, включая тепловые. \n" +
                        "В настоящее время применяется полимерная изоляция, \n" +
                        "а так как современные трубы поступают на трассу", labelStyle);
                dialogAlert(label, "Изоляция сварных стыков");
                return true;
            }
        });

        infGidravlika.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                Label.LabelStyle labelStyle = new Label.LabelStyle();
                labelStyle.font = font1;
                font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                Label label = new Label("До пуска в эксплуатацию необходимо убедиться, \n" +
                        "что механическую прочность и герметичность построенного объекта соответствует всем требованиям, \n" +
                        "выявить и устранить дефектные элементы и тем самым обеспечить надежную работу трубопровода \n" +
                        "на расчетных режимах в период его эксплуатации.", labelStyle);
                dialogAlert(label, "Гидравлическое или пневматическое испытание трубопровода");
                return true;
            }
        });

        infSrezka.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                Label.LabelStyle labelStyle = new Label.LabelStyle();
                labelStyle.font = font1;
                font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                Label label = new Label("Плодородный слой обладает благоприятными свойствами для растений. \n" +
                        "Во избежание экологической катастрофы, в соответствии с требованиями экологов, \n" +
                        "его снимают и перемещают в отдельный отвал.", labelStyle);
                dialogAlert(label, "Срезка плодородного слоя почвы");
                return true;
            }
        });

        infRazrabotka.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                Label.LabelStyle labelStyle = new Label.LabelStyle();
                labelStyle.font = font1;
                font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                Label label = new Label("Устройство грунтовой выемки, \n" +
                        "в которую в последующем будут укладывать трубопровод.", labelStyle);
                dialogAlert(label, "Разработка траншеи");
                return true;
            }
        });

        infPodgotovka_trub.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                Label.LabelStyle labelStyle = new Label.LabelStyle();
                labelStyle.font = font1;
                font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                Label label = new Label("Трубы, изготавливаемые на заводах, \n" +
                        "доставляют на трассу в различное время года и \n" +
                        "разными транспортными средствами поэтому при транспортировке, хранении и погрузочных работ,\n" +
                        "они могут покрыться ржавчиной, изменить точную форму, поэтому необходимо произвести их очистку, \n" +
                        "правку концов, разделку кромок под сварку.", labelStyle);
                dialogAlert(label, "Подготовка труб к сборке");
                return true;
            }
        });

        infOtchistka.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                Label.LabelStyle labelStyle = new Label.LabelStyle();
                labelStyle.font = font1;
                font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                Label label = new Label("Необходимо очистить внутреннюю полость трубопровода от попавших внутрь грунта и воды, \n" +
                        "строительных материалов и других загрязнений.", labelStyle);
                dialogAlert(label, "Очистка полости промывкой/продувкой с пропуском очистных поршней");
                return true;
            }
        });

        infPodgotovka.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                Label.LabelStyle labelStyle = new Label.LabelStyle();
                labelStyle.font = font1;
                font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                Label label = new Label("На дне траншеи после разработки могут быть камни, булыжники, валуны, \n" +
                        "которые могут повредить трубопровод при его укладке, \n" +
                        "поэтому производят присыпку из мягкого грунта, покрывающего все неровности.", labelStyle);
                dialogAlert(label, "Подготовка дна траншеи присыпкой мягким грунтом");
                return true;
            }
        });

        infSooryzhenie.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                Label.LabelStyle labelStyle = new Label.LabelStyle();
                labelStyle.font = font1;
                font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                Label label = new Label("Необходимо для защиты трубопровода от почвенной коррозии в процессе эксплуатации трубопровода. \n" +
                        "Коррозия оказывает пагубное влияние на техническое состояние подземных трубопроводов. \n" +
                        "Под воздействием возможно разрушение трубопровода.", labelStyle);
                dialogAlert(label, "Сооружение средств электрохимической защиты");
                return true;
            }
        });

        preparatoryButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {

                flagWork1=1;
                return true;
            }

        });

        wrapUpButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                flagWork2=1;
                return true;
            }

        });

        mainWorkButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                flagWork3=1;
                return true;
            }

        });

        stage.addActor(table);

        backButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new Main(game,0,0));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(backButton);


        switch (a){

            //Нормальные условия
            case 1:{

                map =new TmxMapLoader().load("Norm/Norm.tmx");
                tiledMapRenderer=new OrthogonalTiledMapRenderer(map);
                Texture texturePlosh = new Texture(Gdx.files.internal("Norm/Дерево.png"));
                 plosh = new Image(texturePlosh);
                plosh.setSize(200, 320);
                plosh.setPosition(Gdx.graphics.getWidth()/2-400,Gdx.graphics.getHeight()-7800/10);
                stage.addActor(plosh);

                Texture texturePlosh2 = new Texture(Gdx.files.internal("Norm/Дерево.png"));
                Image plosh2 = new Image(texturePlosh);
                plosh2.setSize(200, 320);
                plosh2.setPosition(Gdx.graphics.getWidth()/2-700,Gdx.graphics.getHeight()-7800/10);
                stage.addActor(plosh2);

                a=5;
                break;
            }

            //Многолетнемерзлые грунты
            case 2:{
                map =new TmxMapLoader().load("Merzlota/Merzlota.tmx");
                tiledMapRenderer=new OrthogonalTiledMapRenderer(map);
                break;
            }

            //Сильнопересеченная местность
            case 3:{

                map =new TmxMapLoader().load("Mountain/Mountain.tmx");
                tiledMapRenderer=new OrthogonalTiledMapRenderer(map);
                break;
            }

            //Заболоченная местность
            case 4:{

                map =new TmxMapLoader().load("Swamp/Swamp.tmx");
                tiledMapRenderer=new OrthogonalTiledMapRenderer(map);
                break;
            }

        }

        // Размещение строительного городка

       buidTownButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                if(Gdx.input.getDeltaY()!=0){
                    return false;
                }
                return true;
            }

           @Override
           public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
               if(Gdx.input.getDeltaY()==0){
                   Texture texture1 = new Texture(Gdx.files.internal("Tekhnika/Трубовоз.png"));
                   Texture texture2 = new Texture(Gdx.files.internal("Tekhnika/Экскаватор.png"));
                   Texture texture3 = new Texture(Gdx.files.internal("Tekhnika/Автокран.png"));
                   Texture texture4 = new Texture(Gdx.files.internal("Tekhnika/Длинномер.png"));
                   innerTableFunc (1,0,1,1,1, hasBeenClick, "Трубовоз", "Экскаватор","Автокран", "Длинномер", texture1, texture2 ,texture3,texture4);
                   hasBeenClick = !hasBeenClick;
               }
           }
        });

       roudButton.addListener(new InputListener(){
           @Override
           public boolean touchDown(InputEvent event, float x, float y,
                                    int pointer, int button) {
               if(Gdx.input.getDeltaY()!=0){
                   return false;
               }

               return true;
           }

           @Override
           public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
               if(Gdx.input.getDeltaY()==0){
                   Texture texture1 = new Texture(Gdx.files.internal("Tekhnika/Бульдозер.png"));
                   Texture texture2 = new Texture(Gdx.files.internal("Tekhnika/Экскаватор.png"));
                   Texture texture3 = new Texture(Gdx.files.internal("Tekhnika/Харвестер.png"));
                   Texture texture4 = new Texture(Gdx.files.internal("Tekhnika/Плетевоз.png"));
                   innerTableFunc (2,1,0,1,0, hasBeenClick1, "Бульдозер", "Экскаватор","Харвестер", "Плетевоз", texture1, texture2,texture3, texture4);
                   hasBeenClick1 = !hasBeenClick1;
               }
           }
       });

        sooryzhenie.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                if(Gdx.input.getDeltaY()!=0){
                    return false;
                }

                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if(Gdx.input.getDeltaY()==0){
                    Texture texture1 = new Texture(Gdx.files.internal("Tekhnika/Муфта.png"));
                    Texture texture2 = new Texture(Gdx.files.internal("Tekhnika/Анодное заземление.png"));
                    Texture texture3 = new Texture(Gdx.files.internal("Tekhnika/Нагнетательный компрессор.png"));
                    Texture texture4 = new Texture(Gdx.files.internal("Tekhnika/Нагнетательный компрессор.png"));
                    innerTableFunc (12,0,1,0,1, hasBeenClick1, "Муфта", "Анодное \n заземление","Компрессор", "Дренажный \n кабель", texture1, texture2,texture3, texture4);
                }
            }
        });


        ploshadkaButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                if(Gdx.input.getDeltaY()!=0){
                    return false;
                }

                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if(Gdx.input.getDeltaY()==0){
                    Texture texture1 = new Texture(Gdx.files.internal("Tekhnika/Экскаватор.png"));
                    Texture texture2 = new Texture(Gdx.files.internal("Tekhnika/Бульдозер.png"));
                    Texture texture3 = new Texture(Gdx.files.internal("Tekhnika/Трелевочный трактор.png"));
                    Texture texture4 = new Texture(Gdx.files.internal("Tekhnika/Длинномер.png"));
                    innerTableFunc ( 3,1,1,1,1,hasBeenClick2, "Экскаватор", "Бульдозер","Трелевочный \n трактор", "Длинномер", texture1, texture2, texture3, texture4);
                    hasBeenClick2 = !hasBeenClick2;
                }
            }
        });
        geoButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                if(Gdx.input.getDeltaY()!=0){
                    return false;
                }

                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if(Gdx.input.getDeltaY()==0){
                    Texture texture1 = new Texture(Gdx.files.internal("Tekhnika/Пирометр.png"));
                    Texture texture2 = new Texture(Gdx.files.internal("Tekhnika/Тахеометр.png"));
                    Texture texture3 = new Texture(Gdx.files.internal("Tekhnika/Теодолит.png"));
                    Texture texture4 = new Texture(Gdx.files.internal("Tekhnika/Нивелир.png"));
                    innerTableFunc (4,0,1,1,1, hasBeenClick3, "Пирометр", "Тахеометр","Теодолит", "Нивелир", texture1, texture2, texture3, texture4);
                    hasBeenClick3 = !hasBeenClick3;
                }
            }

        });
        treeButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                if(Gdx.input.getDeltaY()!=0){
                    return false;
                }

                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if(Gdx.input.getDeltaY()==0){
                    Texture texture1 = new Texture(Gdx.files.internal("Tekhnika/Харвестер.png"));
                    Texture texture2 = new Texture(Gdx.files.internal("Tekhnika/Бензомоторная пила.png"));
                    Texture texture3 = new Texture(Gdx.files.internal("Tekhnika/Гидромолот.png"));
                    Texture texture4 = new Texture(Gdx.files.internal("Tekhnika/Кусторез.png"));
                    innerTableFunc ( 5,1,1,0,1,hasBeenClick4, "Харвестер", "Бензомоторная \n               пила","Гидромолот", "Кусторез", texture1, texture2,texture3,texture4);
                    hasBeenClick4 = !hasBeenClick4;
                }
            }
        });
        planButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                if(Gdx.input.getDeltaY()!=0){
                    return false;
                }

                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if(Gdx.input.getDeltaY()==0){
                    Texture texture1 = new Texture(Gdx.files.internal("Tekhnika/Экскаватор.png"));
                    Texture texture2 = new Texture(Gdx.files.internal("Tekhnika/Бульдозер.png"));
                    Texture texture3 = new Texture(Gdx.files.internal("Tekhnika/Грейдер.png"));
                    Texture texture4 = new Texture(Gdx.files.internal("Tekhnika/Грейфер.png"));
                    innerTableFunc ( 6,0,1,1,0,hasBeenClick5, "Экскаватор", "Бульдозер","Грейдер", "Грейфер",  texture1,  texture2,  texture3,  texture4);
                    hasBeenClick5 = !hasBeenClick5;
                }
            }
        });


        ustrButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                if(Gdx.input.getDeltaY()!=0){
                    return false;
                }

                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if(Gdx.input.getDeltaY()==0){
                    Texture texture1 = new Texture(Gdx.files.internal("Tekhnika/Бульдозер.png"));
                    Texture texture2 = new Texture(Gdx.files.internal("Tekhnika/Экскаватор.png"));
                    Texture texture3 = new Texture(Gdx.files.internal("Tekhnika/Трелевочный трактор.png"));
                    Texture texture4 = new Texture(Gdx.files.internal("Tekhnika/Плетевоз.png"));
                    innerTableFunc (7,1,0,1,0, hasBeenClick6, "Бульдозер", "Экскаватор","Трелевочный \n трактор", "Плетевоз", texture1, texture2, texture3, texture4);
                    hasBeenClick6 = !hasBeenClick6;
                }
            }
        });

        priemka.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                if(Gdx.input.getDeltaY()!=0){
                    return false;
                }

                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if(Gdx.input.getDeltaY()==0){
                    Texture texture1 = new Texture(Gdx.files.internal("Tekhnika/Трубоукладчик.png"));
                    Texture texture2 = new Texture(Gdx.files.internal("Tekhnika/Трубовоз.png"));
                    Texture texture3 = new Texture(Gdx.files.internal("Tekhnika/Мостовой кран.png"));
                    Texture texture4 = new Texture(Gdx.files.internal("Tekhnika/Траверса грузоподъемная.png"));
                    innerTableFunc ( 8,0,1,1,1,hasBeenClick7, "Трубоукладчик", "Трубовоз","Мостовой кран", "Траверса",  texture1,  texture2,  texture3,  texture4);
                    hasBeenClick7 = !hasBeenClick7;
                }
            }
        });

        dostavka.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                if(Gdx.input.getDeltaY()!=0){
                    return false;
                }

                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if(Gdx.input.getDeltaY()==0){
                    Texture texture1 = new Texture(Gdx.files.internal("Tekhnika/Трубовоз.png"));
                    Texture texture2 = new Texture(Gdx.files.internal("Tekhnika/Кран.png"));
                    Texture texture3 = new Texture(Gdx.files.internal("Tekhnika/Плетевоз.png"));
                    Texture texture4 = new Texture(Gdx.files.internal("Tekhnika/Жд транспорт.png"));
                    innerTableFunc ( 9,1,0,1,0,hasBeenClick8, "Трубовоз", "Кран","Плетевоз", "Ж/д транспорт",  texture1,  texture2,  texture3,  texture4);
                    hasBeenClick8 = !hasBeenClick8;
                }
            }
        });


        skladirovanie.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                if(Gdx.input.getDeltaY()!=0){
                    return false;
                }

                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if(Gdx.input.getDeltaY()==0){
                    Texture texture1 = new Texture(Gdx.files.internal("Tekhnika/Лежки.png"));
                    Texture texture2 = new Texture(Gdx.files.internal("Tekhnika/Штабеля.png"));
                    Texture texture3 = new Texture(Gdx.files.internal("Tekhnika/Трубоукладчик.png"));
                    Texture texture4 = new Texture(Gdx.files.internal("Tekhnika/Траверса грузоподъемная.png"));
                    innerTableFunc ( 10,1,1,1,1,hasBeenClick9, "Лежки", "Штабеля","Трубоукладчик", "Траверса",  texture1,  texture2,  texture3,  texture4);
                    hasBeenClick9 = !hasBeenClick9;
                }
            }
        });

        razvozka.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                if(Gdx.input.getDeltaY()!=0){
                    return false;
                }

                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if(Gdx.input.getDeltaY()==0){
                    Texture texture1 = new Texture(Gdx.files.internal("Tekhnika/Трубовоз.png"));
                    Texture texture2 = new Texture(Gdx.files.internal("Tekhnika/Грейдер.png"));
                    Texture texture3 = new Texture(Gdx.files.internal("Tekhnika/Экскаватор.png"));
                    Texture texture4 = new Texture(Gdx.files.internal("Tekhnika/Бульдозер.png"));
                    innerTableFunc ( 11,1,0,0,0,hasBeenClick10, "Трубовоз", "Грейдер","Экскаватор- \n погрузчик", "Бульдозер",  texture1,  texture2,  texture3,  texture4);
                    hasBeenClick10 = !hasBeenClick10;
                }
            }
        });


        srezka.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                if(Gdx.input.getDeltaY()!=0){
                    return false;
                }

                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if(Gdx.input.getDeltaY()==0){
                    Texture texture1 = new Texture(Gdx.files.internal("Tekhnika/Экскаватор.png"));
                    Texture texture2 = new Texture(Gdx.files.internal("Tekhnika/Бульдозер.png"));
                    Texture texture3 = new Texture(Gdx.files.internal("Tekhnika/Грейфер.png"));
                    Texture texture4 = new Texture(Gdx.files.internal("Tekhnika/Гидромолот.png"));
                    innerTableFunc ( 13,0,1,0,0,hasBeenClick7, "Экскаватор", "Бульдозер","Грейфер", "Гидромолот",  texture1,  texture2,  texture3,  texture4);

                }
            }
        });

        razrabotka.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                if(Gdx.input.getDeltaY()!=0){
                    return false;
                }

                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if(Gdx.input.getDeltaY()==0){
                    Texture texture1 = new Texture(Gdx.files.internal("Tekhnika/Обратная лопата.png"));
                    Texture texture2 = new Texture(Gdx.files.internal("Tekhnika/Прямая лопата.png"));
                    Texture texture3 = new Texture(Gdx.files.internal("Tekhnika/Грейфер.png"));
                    Texture texture4 = new Texture(Gdx.files.internal("Tekhnika/Драглайн.png"));
                    innerTableFunc ( 14,1,0,0,0,hasBeenClick7, "Обратная \n лопата", "Прямая лопата","Грейфер", "Драглайн",  texture1,  texture2,  texture3,  texture4);

                }
            }
        });

        podgotovka.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                if(Gdx.input.getDeltaY()!=0){
                    return false;
                }

                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if(Gdx.input.getDeltaY()==0){
                    Texture texture1 = new Texture(Gdx.files.internal("Tekhnika/Лопата.png"));
                    Texture texture2 = new Texture(Gdx.files.internal("Tekhnika/Экскаватор.png"));
                    Texture texture3 = new Texture(Gdx.files.internal("Tekhnika/Бульдозер.png"));
                    Texture texture4 = new Texture(Gdx.files.internal("Tekhnika/Трубоукладчик.png"));
                    innerTableFunc ( 15,0,1,0,0,hasBeenClick7, "Вручную \n       (лопатами)", "Экскаватор","Бульдозер", "Трубоукладчик",  texture1,  texture2,  texture3,  texture4);

                }
            }
        });

        podgotovka_trub.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                if(Gdx.input.getDeltaY()!=0){
                    return false;
                }

                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if(Gdx.input.getDeltaY()==0){
                    Texture texture1 = new Texture(Gdx.files.internal("Tekhnika/Шлифмашинка.png"));
                    Texture texture2 = new Texture(Gdx.files.internal("Tekhnika/Лента ППУ.png"));
                    Texture texture3 = new Texture(Gdx.files.internal("Tekhnika/Газосварочный аппарат.png"));
                    Texture texture4 = new Texture(Gdx.files.internal("Tekhnika/Лежки.png"));
                    innerTableFunc ( 16,1,0,1,0,hasBeenClick7, "Шлифмашинка", "Лента ППУ","Газосварочный аппарат", "Лежки",  texture1,  texture2,  texture3,  texture4);

                }
            }
        });

        sborka.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                if(Gdx.input.getDeltaY()!=0){
                    return false;
                }

                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if(Gdx.input.getDeltaY()==0){
                    Texture texture1 = new Texture(Gdx.files.internal("Tekhnika/Центратор.png"));
                    Texture texture2 = new Texture(Gdx.files.internal("Tekhnika/Прихватка.png"));
                    Texture texture3 = new Texture(Gdx.files.internal("Tekhnika/Шлифмашинка.png"));
                    Texture texture4 = new Texture(Gdx.files.internal("Tekhnika/Сварочная дуга.png"));
                    innerTableFunc ( 17,1,1,0,1,hasBeenClick7, "Центратор", "Прихватка","Шлифмашинка", "Сварочная дуга",  texture1,  texture2,  texture3,  texture4);

                }
            }
        });

        control.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                if(Gdx.input.getDeltaY()!=0){
                    return false;
                }

                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if(Gdx.input.getDeltaY()==0){
                    Texture texture1 = new Texture(Gdx.files.internal("Tekhnika/Праймер.png"));
                    Texture texture2 = new Texture(Gdx.files.internal("Tekhnika/Глаза.png"));
                    Texture texture3 = new Texture(Gdx.files.internal("Tekhnika/Магнитный порошок.png"));
                    Texture texture4 = new Texture(Gdx.files.internal("Tekhnika/Дефектоскоп.png"));
                    innerTableFunc ( 18,0,1,1,1,hasBeenClick7, "Праймер", "Визуальный \n осмотр","Магнитный \n порошок", "Дефектоскоп",  texture1,  texture2,  texture3,  texture4);

                }
            }
        });

        izolyation.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                if(Gdx.input.getDeltaY()!=0){
                    return false;
                }

                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if(Gdx.input.getDeltaY()==0){
                    Texture texture1 = new Texture(Gdx.files.internal("Tekhnika/Термоусадочная муфта.png"));
                    Texture texture2 = new Texture(Gdx.files.internal("Tekhnika/Магнитный порошок.png"));
                    Texture texture3 = new Texture(Gdx.files.internal("Tekhnika/Лента ППУ.png"));
                    Texture texture4 = new Texture(Gdx.files.internal("Tekhnika/Праймер.png"));
                    innerTableFunc ( 19,1,0,1,1,hasBeenClick7, "Термоусаживаемая \n муфта", "Магнитный \n порошок","Лента ППУ", "Праймер",  texture1,  texture2,  texture3,  texture4);

                }
            }
        });

        ukladka.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                if(Gdx.input.getDeltaY()!=0){
                    return false;
                }

                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if(Gdx.input.getDeltaY()==0){
                    Texture texture1 = new Texture(Gdx.files.internal("Tekhnika/Троллейная подвеска.png"));
                    Texture texture2 = new Texture(Gdx.files.internal("Tekhnika/Бульдозер.png"));
                    Texture texture3 = new Texture(Gdx.files.internal("Tekhnika/Трубоукладчик.png"));
                    Texture texture4 = new Texture(Gdx.files.internal("Tekhnika/Грейфер.png"));
                    innerTableFunc ( 20,1,0,1,0,hasBeenClick7, "Троллейная \n       подвеска", "Бульдозер","Трубоукладчик", "Траверса",  texture1,  texture2,  texture3,  texture4);

                }
            }
        });

        zasypka.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                if(Gdx.input.getDeltaY()!=0){
                    return false;
                }

                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if(Gdx.input.getDeltaY()==0){
                    Texture texture1 = new Texture(Gdx.files.internal("Tekhnika/Экскаватор.png"));
                    Texture texture2 = new Texture(Gdx.files.internal("Tekhnika/Бульдозер.png"));
                    Texture texture3 = new Texture(Gdx.files.internal("Tekhnika/Лопата.png"));
                    Texture texture4 = new Texture(Gdx.files.internal("Tekhnika/Трубоукладчик.png"));
                    innerTableFunc ( 21,1,1,0,0,hasBeenClick7, "Экскаватор", "Бульдозер","Вручную \n     (лопатами)", "Трубоукладчик",  texture1,  texture2,  texture3,  texture4);

                }
            }
        });

        otchistka.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                if(Gdx.input.getDeltaY()!=0){
                    return false;
                }

                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if(Gdx.input.getDeltaY()==0){
                    Texture texture1 = new Texture(Gdx.files.internal("Tekhnika/Дефектоскоп.png"));
                    Texture texture2 = new Texture(Gdx.files.internal("Tekhnika/Поршень.png"));
                    Texture texture3 = new Texture(Gdx.files.internal("Tekhnika/Компрессор.png"));
                    Texture texture4 = new Texture(Gdx.files.internal("Tekhnika/Пирометр.png"));
                    innerTableFunc ( 22,0,1,1,0,hasBeenClick7, "Дефектоскоп", "Поршень","Компрессор", "Пирометр",  texture1,  texture2,  texture3,  texture4);

                }
            }
        });

        gidravlika.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                if(Gdx.input.getDeltaY()!=0){
                    return false;
                }

                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if(Gdx.input.getDeltaY()==0){
                    Texture texture1 = new Texture(Gdx.files.internal("Tekhnika/Манометр.png"));
                    Texture texture2 = new Texture(Gdx.files.internal("Tekhnika/Опрессовочный агрегат.png"));
                    Texture texture3 = new Texture(Gdx.files.internal("Tekhnika/Поршень.png"));
                    Texture texture4 = new Texture(Gdx.files.internal("Tekhnika/Пьезометр.png"));
                    innerTableFunc ( 23,1,1,0,0,hasBeenClick7, "Манометр", "Опрессовочный \n     агрегат","Поршень", "Пьезометр",  texture1,  texture2,  texture3,  texture4);

                }
            }
        });



      btnClose.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
             //   close =1;
                buidTownButtonInventory.remove();

                return true;
            }
        });

        coinImg = new Image(textureCoin);
        coinImg.setSize(textureCoin.getWidth()/5, textureCoin.getHeight()/5);
        coinImg.setPosition(Gdx.graphics.getWidth()-18*coinImg.getWidth(),Gdx.graphics.getHeight()-12*coinImg.getHeight()/10);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
      //  Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {



        stage.act(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClearColor(100,100,100,0);
        Gdx.gl.glBlendColor(GL20.GL_SRC_ALPHA,GL20.GL_ONE_MINUS_SRC_ALPHA,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        timeSeconds +=Gdx.graphics.getDeltaTime();

        camera.update();
        tiledMapRenderer.setView(camera.combined,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        tiledMapRenderer.render();

        stage.draw();
        batch.begin();


        sec = (int) timeSeconds;

        stage.addActor(timerImg);
        stage.addActor(coinImg);



        if (right==3){
            right = 0;

            new Thread(new Runnable() {
                @Override
                public void run() {
                    long time = System.currentTimeMillis();
                    while (System.currentTimeMillis()<time+1200){}
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            buidTownButtonInventory.remove();
                            check(buidTownButton.getText().toString());
                            if (yes1==1){
                                Texture textureTown = new Texture(Gdx.files.internal("Tekhnika/Городок.png"));
                                Image town = new Image(textureTown);
                                town.setSize(550, 450);
                                town.setPosition(Gdx.graphics.getWidth()-town.getWidth()-80,Gdx.graphics.getHeight()-19*town.getHeight()/10);
                                stage.addActor(town);
                            }
                        }
                    });
                }
            }).start();

        }

        if (right1==2){
            right1 = 0;

            new Thread(new Runnable() {
                @Override
                public void run() {
                    long time = System.currentTimeMillis();
                    while (System.currentTimeMillis()<time+1200){}
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            buidTownButtonInventory.remove();
                            check(roudButton.getText().toString());
                            if (yes2==1){
                                Texture textureRoad = new Texture(Gdx.files.internal("Step/Дорога.png"));
                                Image road = new Image(textureRoad);
                                road.setSize(450, 130);
                                road.setPosition(0,Gdx.graphics.getHeight()-45-19*450/10);
                                stage.addActor(road);
                            }
                        }
                    });
                }
            }).start();

        }

        if (right2==4){
            right2 = 0;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long time = System.currentTimeMillis();
                    while (System.currentTimeMillis()<time+1200){}
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            buidTownButtonInventory.remove();
                            check(ploshadkaButton.getText().toString());

                            if (yes3==1){

                                Texture texturePlosh = new Texture(Gdx.files.internal("Norm/plosh.png"));
                                Image plosh = new Image(texturePlosh);
                                plosh.setSize(350, 120);
                                plosh.setPosition(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()-5-20*450/10);
                                stage.addActor(plosh);
                            }

                        }
                    });
                }
            }).start();
        }

        if (right3==3){
            right3 = 0;

            new Thread(new Runnable() {
                @Override
                public void run() {
                    long time = System.currentTimeMillis();
                    while (System.currentTimeMillis()<time+1200){}
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            buidTownButtonInventory.remove();
                            check(geoButton.getText().toString());

                            if (yes4==1){

                                Texture texturePlosh2 = new Texture(Gdx.files.internal("Norm/Дерево3.png"));
                                Image plosh2 = new Image(texturePlosh2);
                                plosh2.setSize(200, 320);
                                plosh2.setPosition(Gdx.graphics.getWidth()/2-700,Gdx.graphics.getHeight()-7800/10);
                                stage.addActor(plosh2);

                            }

                        }
                    });
                }
            }).start();
        }

        if (right4==3){
            right4 = 0;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long time = System.currentTimeMillis();
                    while (System.currentTimeMillis()<time+1200){}
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            buidTownButtonInventory.remove();
                            check(treeButton.getText().toString());

                            if (yes5==1){

                                plosh.remove();

                            }

                        }
                    });
                }
            }).start();
        }

        if (right5==2){
            right5 = 0;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long time = System.currentTimeMillis();
                    while (System.currentTimeMillis()<time+1200){}
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            buidTownButtonInventory.remove();
                            check(planButton.getText().toString());

                            if (yes6==1){

                                Texture texturePlosh3 = new Texture(Gdx.files.internal("Norm/Песок.png"));
                                Image plosh3 = new Image(texturePlosh3);
                                plosh3.setSize(200, 220);
                                plosh3.setPosition(Gdx.graphics.getWidth()/2-400,Gdx.graphics.getHeight()-9100/10);
                                stage.addActor(plosh3);

                            }

                        }
                    });
                }
            }).start();
        }

        if (right6==2){
            right6 = 0;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long time = System.currentTimeMillis();
                    while (System.currentTimeMillis()<time+1200){}
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            buidTownButtonInventory.remove();
                            check(ustrButton.getText().toString());

                        }
                    });
                }
            }).start();
        }

        if (right7==3){
            right7 = 0;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long time = System.currentTimeMillis();
                    while (System.currentTimeMillis()<time+1200){}
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            buidTownButtonInventory.remove();
                            checkwrapUp(priemka.getText().toString());

                        }
                    });
                }
            }).start();
        }
        if (right8==2){
            right8 = 0;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long time = System.currentTimeMillis();
                    while (System.currentTimeMillis()<time+1200){}
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            buidTownButtonInventory.remove();
                            checkwrapUp(dostavka.getText().toString());

                        }
                    });
                }
            }).start();
        }
        if (right9==4){
            right9 = 0;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long time = System.currentTimeMillis();
                    while (System.currentTimeMillis()<time+1200){}
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            buidTownButtonInventory.remove();
                            checkwrapUp(skladirovanie.getText().toString());

                        }
                    });
                }
            }).start();
        }
        if (right10==1){
            right10 = 0;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long time = System.currentTimeMillis();
                    while (System.currentTimeMillis()<time+1200){}
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            buidTownButtonInventory.remove();
                            checkwrapUp(razvozka.getText().toString());

                        }
                    });
                }
            }).start();
        }

        if (right11==2){
            right11 = 0;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long time = System.currentTimeMillis();
                    while (System.currentTimeMillis()<time+1200){}
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            buidTownButtonInventory.remove();
                            checkMain(sooryzhenie.getText().toString());
                        }
                    });
                }
            }).start();
        }

        if (right12==1){
            right12 = 0;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long time = System.currentTimeMillis();
                    while (System.currentTimeMillis()<time+1200){}
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            buidTownButtonInventory.remove();
                            checkMain(srezka.getText().toString());
                        }
                    });
                }
            }).start();
        }

        if (right13==1){
            right13 = 0;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long time = System.currentTimeMillis();
                    while (System.currentTimeMillis()<time+1200){}
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            buidTownButtonInventory.remove();
                            checkMain(razrabotka.getText().toString());

                        }
                    });
                }
            }).start();
        }

        if (right14==1){
            right14 = 0;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long time = System.currentTimeMillis();
                    while (System.currentTimeMillis()<time+1200){}
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            buidTownButtonInventory.remove();
                            checkMain(podgotovka.getText().toString());

                        }
                    });
                }
            }).start();
        }

        if (right15==2){
            right15 = 0;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long time = System.currentTimeMillis();
                    while (System.currentTimeMillis()<time+1200){}
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            buidTownButtonInventory.remove();
                            checkMain(podgotovka_trub.getText().toString());

                        }
                    });
                }
            }).start();
        }

        if (right16==3){
            right16 = 0;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long time = System.currentTimeMillis();
                    while (System.currentTimeMillis()<time+1200){}
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            buidTownButtonInventory.remove();
                            checkMain(sborka.getText().toString());

                        }
                    });
                }
            }).start();
        }

        if (right17==3){
            right17 = 0;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long time = System.currentTimeMillis();
                    while (System.currentTimeMillis()<time+1200){}
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            buidTownButtonInventory.remove();
                            checkMain(control.getText().toString());

                        }
                    });
                }
            }).start();
        }

        if (right18==3){
            right18 = 0;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long time = System.currentTimeMillis();
                    while (System.currentTimeMillis()<time+1200){}
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            buidTownButtonInventory.remove();
                            checkMain(izolyation.getText().toString());

                        }
                    });
                }
            }).start();
        }

        if (right19==2){
            right19 = 0;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long time = System.currentTimeMillis();
                    while (System.currentTimeMillis()<time+1200){}
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            buidTownButtonInventory.remove();
                            checkMain(ukladka.getText().toString());

                        }
                    });
                }
            }).start();
        }

        if (right20==2){
            right20 = 0;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long time = System.currentTimeMillis();
                    while (System.currentTimeMillis()<time+1200){}
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            buidTownButtonInventory.remove();
                            checkMain(zasypka.getText().toString());

                        }
                    });
                }
            }).start();
        }

        if (right21==2){
            right21 = 0;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long time = System.currentTimeMillis();
                    while (System.currentTimeMillis()<time+1200){}
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            buidTownButtonInventory.remove();
                            checkMain(otchistka.getText().toString());

                        }
                    });
                }
            }).start();
        }

        if (right22==2){
            right22 = 0;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long time = System.currentTimeMillis();
                    while (System.currentTimeMillis()<time+1200){}
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            buidTownButtonInventory.remove();
                            checkMain(gidravlika.getText().toString());

                        }
                    });
                }
            }).start();
        }

        FontCoin.draw(batch, Math.round(coin)+" y.e.", Gdx.graphics.getWidth()/8,Gdx.graphics.getHeight()-5*Gdx.graphics.getHeight()/22/4);

        if( flagWork1==1){
                table.remove();
                stage.addActor(korzina);
                flagWork1=5;
        }
        if (flagWork2 ==1){
          //  checkOsn(wrapUpButton.getText().toString());
            flagWork2=5;
            if (endPreparatory==1){
                table.remove();
                stage.addActor(korzinaWrap);

            }
            else {

                Label.LabelStyle labelStyle = new Label.LabelStyle();
                labelStyle.font = font1;
                font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                Label label = new Label("УПС! \n Нужно начать с других работ!", labelStyle);
                String title = "Ошибка в последовательности";
                dialogAlert(label, title);

            }

        }
        if (flagWork3==1){
       //     checkOsn(mainWorkButton.getText().toString());
            flagWork3=5;
            if (endWrap==1){
                table.remove();
                stage.addActor(korzinaMain);
            }
            else {
                Label.LabelStyle labelStyle = new Label.LabelStyle();
                labelStyle.font = font1;
                font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                Label label = new Label("УПС! \n Выбрана неправильная работа!", labelStyle);
                String title = "Ошибка в последовательности";
                dialogAlert(label, title);
                flagWork3=5;
            }
        }

        FontTimer.draw(batch, timeToString (sec), 1*Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()-5*Gdx.graphics.getHeight()/22/4);

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

    }

    public String timeToString (long secund){
        long    hour = secund/3600,
                min=secund/60%60,
                sec=secund/1 %60;

        return String.format("%02d:%02d:%02d",hour,min,sec);
    }

    public boolean checkwrapUp(String keyPressed){
        if (keyPressed.equals(rightwrapUp[currentButtonwrap])){
            currentButtonwrap++;
            switch (currentButtonwrap){
                case 1:{
                    yes8=1;
                }break;
                case 2:{
                    yes9=1;
                }break;
                case 3:{
                    yes10=1;
                }break;
                case 4:{
                    yes11=1;
                }break;

            }

            if(currentButtonwrap == rightwrapUp.length){
                currentButtonwrap=0;
                Label.LabelStyle labelStyle = new Label.LabelStyle();
                labelStyle.font = font1;
                font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                Label label = new Label("Работы на этом этапе завершены. \n Перейдите к следующему этапу строительства", labelStyle);
                String title = "Готово!";
                dialogAlert(label,title);
                endWrap=1;
                return true;
            }
        }
        else {
            Label.LabelStyle labelStyle = new Label.LabelStyle();
            labelStyle.font = font1;
            font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            Label label = new Label("УПС! \n Выбрана неправильная работа!", labelStyle);
            String title = "Ошибка в последовательности";
            dialogAlert(label, title);
            //currentButton = 0;
        }
        return false;
    }

    public boolean checkMain(String keyPressed){
        if (keyPressed.equals(rightmainWork[currentButtonMain])){
            currentButtonMain++;
            switch (currentButtonMain){
                case 1:{
                    yes12=1;
                }break;
                case 2:{
                    yes13=1;
                }break;
                case 3:{
                    yes14=1;
                }break;
                case 4:{
                    yes15=1;
                }break;
                case 5:{
                    yes16=1;
                }break;
                case 6:{
                    yes17=1;
                }break;
                case 7:{
                    yes18=1;
                }break;
                case 8:{
                    yes19=1;
                }break;
                case 9:{
                    yes20=1;
                }break;
                case 10:{
                    yes21=1;
                }break;
                case 11:{
                    yes22=1;
                }break;
                case 12:{
                    yes23=1;
                }break;

            }

            if(currentButtonMain == rightmainWork.length){
                currentButtonMain=0;
                buidTownButtonInventory.remove();
                Label.LabelStyle labelStyle = new Label.LabelStyle();
                labelStyle.font = font1;
                font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                raitItog = (1/(1000000-coin))*(1/timeSeconds)*1000000;
                raitItog*=100000;
                result= (int)Math.round(raitItog);
                Label label = new Label("Строительство завершено! \n Рейтинг:"+result, labelStyle);
                boolean exists = Gdx.files.local("info.txt").exists();
                if (exists){
                    file = Gdx.files.local("info.txt");
                    textFile = file.readString();
                    wordsArray = textFile .split("\\r?\\n");
                    textFile =wordsArray[0];
                    rait=wordsArray[1];
                }

                String raitNorm;
                int raitNormInt;
                if (wordsArray.length>2) {
                    raitNorm=wordsArray[3];
                    raitNormInt=Integer.parseInt(raitNorm);
                    if(result>raitNormInt) {
                        raiting = Integer.parseInt(rait);
                        raiting += result-raitNormInt;
                        file.writeString(textFile, false);
                        file.writeString("\n", true);
                        file.writeString(raiting + "", true);
                        file.writeString("\n", true);
                        file.writeString(textFile + "_" + "Нормальные условия", true);
                        file.writeString("\n", true);
                        file.writeString(result + "", true);
                    }
                }
                else
                {
                    raiting = Integer.parseInt(rait);
                    raiting += result;
                    file.writeString(textFile, false);
                    file.writeString("\n", true);
                    file.writeString(raiting + "", true);
                    file.writeString("\n", true);
                    file.writeString(textFile + "_" + "Нормальные условия", true);
                    file.writeString("\n", true);
                    file.writeString(result + "", true);
                }

                String title = "Завершено";
                dialogAlert(label,title);
                endMain=1;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long time = System.currentTimeMillis();
                        while (System.currentTimeMillis()<time+1200){}
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                game.setScreen(new Main(game,0,0));
                            }
                        });
                    }
                }).start();


                }
                return true;
            }
        else {
            Label.LabelStyle labelStyle = new Label.LabelStyle();
            labelStyle.font = font1;
            font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            Label label = new Label("УПС! \n Выбрана неправильная работа!", labelStyle);
            String title = "Ошибка в последовательности";
            dialogAlert(label, title);
            //currentButton = 0;
        }
        return false;
    }

    public boolean check(String keyPressed){
        if (keyPressed.equals(rightLogicPreparatory[currentButton])){
            currentButton++;
            switch (currentButton){
                case 1:{
                    yes1=1;
                }break;
                case 2:{
                    yes2=1;
                }break;
                case 3:{
                    yes3=1;
                }break;
                case 4:{
                    yes4=1;
                }break;
                case 5:{
                    yes5=1;
                }break;
                case 6:{
                    yes6=1;
                }break;
                case 7:{
                    yes7=1;
                }break;
            }

            if(currentButton == rightLogicPreparatory.length){
                currentButton=0;
                Label.LabelStyle labelStyle = new Label.LabelStyle();
                labelStyle.font = font1;
                font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                Label label = new Label("Работы на этом этапе завершены. \n Перейдите к следующему этапу строительства", labelStyle);
                String title = "Готово!";
                dialogAlert(label,title);

                if (yes7==1){

                    Texture texturePlosh4 = new Texture(Gdx.files.internal("Norm/Грузовик.png"));
                    Image plosh4 = new Image(texturePlosh4);
                    plosh4.setSize(400, 250);
                    plosh4.setPosition(Gdx.graphics.getWidth()/2-700,Gdx.graphics.getHeight()-8500/10);
                    stage.addActor(plosh4);

                }
                endPreparatory=1;
                return true;
            }
        }
        else {
            Label.LabelStyle labelStyle = new Label.LabelStyle();
            labelStyle.font = font1;
            font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            Label label = new Label("УПС! \n Выбрана неправильная работа!", labelStyle);
            String title = "Ошибка в последовательности";
            dialogAlert(label, title);

            //currentButton = 0;
        }
        return false;
    }

    public boolean checkOsn(String keyPressed){
        if (keyPressed.equals(rightLogicOsn[currentButtonOsn])){
            currentButtonOsn++;
            switch (currentButtonOsn){
                case 1:{
                    yes1Osn=1;
                }break;
                case 2:{
                    yes2Osn=1;
                }break;
                case 3:{
                    yes3Osn=1;
                }break;
            }

            if(currentButtonOsn == rightLogicOsn.length){
                currentButtonOsn=0;
                return true;
            }
        }
        else {
            Label.LabelStyle labelStyle = new Label.LabelStyle();
            labelStyle.font = font1;
            font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            Label label = new Label("УПС! \n Выбрана неправильная работа!", labelStyle);
            String title = "Ошибка в последовательности";
            dialogAlert(label, title);

            //currentButton = 0;
        }
        return false;
    }

    public void innerTableFunc (final int quest, final int a, final int b, final int c, final int d, boolean hasBeenClick, String labelInstr1, String labelInstr2, String labelInstr3, String labelInstr4, Texture texture1, Texture texture2, Texture texture3, Texture texture4){


//        if (! hasBeenClick){

            if (buidTownButtonInventory!=null){
                buidTownButtonInventory.remove();
                hasBeenClick=false;
            }
            buidTownButtonInventory = new Table();
            buidTownButtonInventory .defaults().height(210);
            buidTownButtonInventory .defaults().width(1800);
            buidTownButtonInventory.setPosition(Gdx.graphics.getWidth()/2-330, Gdx.graphics.getHeight()/2-250);
            buidTownButtonInventory.setSize(660,660);

            Image coinImg = new Image(textureCoin);
            coinImg.setSize(50, 50);
            Label.LabelStyle labelStyle = new Label.LabelStyle();
            labelStyle.font = font2;
            font2.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            Label label = new Label(labelInstr1,labelStyle);
            Label label2 = new Label("Label2",uiSkin);
            Label label1 = new Label("",uiSkin);
            label.setAlignment(Align.top);

            Pixmap bgPixmap = new Pixmap(1,1,Pixmap.Format.RGB565);
            bgPixmap.setColor(Color.GRAY);
            bgPixmap.fill();
            TextureRegionDrawable textureRegionDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));
            buidTownButtonInventory.setBackground(textureRegionDrawable);

            final Table innerTable = new Table();
            Pixmap bgPixmapInnerTable = new Pixmap(1,1,Pixmap.Format.RGB565);
            bgPixmapInnerTable.setColor(Color.GOLD);
            bgPixmapInnerTable.fill();
            TextureRegionDrawable textureRegionDrawableInner = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmapInnerTable)));
            innerTable.setBackground(textureRegionDrawableInner);
            innerTable.add(new Label(labelInstr1,labelStyle)).size(150,20).align(Align.center);
            innerTable.row();
            innerTable.add(new Image(texture1)).size(300,220).padBottom(20);

            innerTable.addListener(new InputListener(){
                @Override
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    Pixmap bgPixmapInnerTableTapNot = new Pixmap(1,1,Pixmap.Format.RGB565);
                    if (a==1){
                        bgPixmapInnerTableTapNot.setColor(Color.GREEN);
                        switch (quest) {
                            case 1: {
                                right++;
                            }break;
                            case 2: {
                                right1++;
                            }break;
                            case 3:{
                                right2++;
                            }break;
                            case 4:{
                                right3++;
                            }break;
                            case 5:{
                                right4++;
                            }break;
                            case 6:{
                                right5++;
                            }break;
                            case 7:{
                                right6++;
                            }break;
                            case 8:{
                                right7++;
                            }break;
                            case 9:{
                                right8++;
                            }break;
                            case 10:{
                                right9++;
                            }break;
                            case 11:{
                                right10++;
                            }break;
                            case 12:{
                                right11++;
                            }break;
                            case 13:{
                                right12++;
                            }break;
                            case 14:{
                                right13++;
                            }break;
                            case 15:{
                                right14++;
                            }break;
                            case 16:{
                                right15++;
                            }break;
                            case 17:{
                                right16++;
                            }break;
                            case 18:{
                                right17++;
                            }break;
                            case 19:{
                                right18++;
                            }break;
                            case 20:{
                                right19++;
                            }break;
                            case 21:{
                                right20++;
                            }break;
                            case 22:{
                                right21++;
                            }break;
                            case 23:{
                                right22++;
                            }break;
                        }
                    }
                    else {
                        bgPixmapInnerTableTapNot.setColor(Color.RED);
                        if (coin>=200 ){
                            coin-=200;
                        }
                        else {
                            moneyError();
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    long time = System.currentTimeMillis();
                                    while (System.currentTimeMillis()<time+1200){}
                                    Gdx.app.postRunnable(new Runnable() {
                                        @Override
                                        public void run() {
                                            game.setScreen(new Main(game,0,0));
                                        }
                                    });
                                }
                            }).start();
                        }

                    }
                    bgPixmapInnerTableTapNot.fill();
                    TextureRegionDrawable textureRegionDrawableInnerTapNot = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmapInnerTableTapNot)));
                    innerTable.setBackground(textureRegionDrawableInnerTapNot);
                    rightTekhnika();

                }
                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });

            final Table innerTable2 = new Table();
            innerTable2.setBackground(textureRegionDrawableInner);
            innerTable2.add(new Label(labelInstr2,labelStyle)).size(150,20).align(Align.center);
            innerTable2.row();
            innerTable2.add(new Image(texture2)).size(300,220).padBottom(20);

            innerTable2.addListener(new InputListener(){
                @Override
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    Pixmap bgPixmapInnerTableTapNot = new Pixmap(1,1,Pixmap.Format.RGB565);
                    if (b==1){
                        bgPixmapInnerTableTapNot.setColor(Color.GREEN);
                        switch (quest) {
                            case 1: {
                                right++;
                            }break;
                            case 2: {
                                right1++;
                            }break;
                            case 3:{
                                right2++;
                            }break;
                            case 4:{
                                right3++;
                            }break;
                            case 5:{
                                right4++;
                            }break;
                            case 6:{
                                right5++;
                            }break;
                            case 7:{
                                right6++;
                            }break;
                            case 8:{
                                right7++;
                            }break;
                            case 9:{
                                right8++;
                            }break;
                            case 10:{
                                right9++;
                            }break;
                            case 11:{
                                right10++;
                            }break;
                            case 12:{
                                right11++;
                            }break;
                            case 13:{
                                right12++;
                            }break;
                            case 14:{
                                right13++;
                            }break;
                            case 15:{
                                right14++;
                            }break;
                            case 16:{
                                right15++;
                            }break;
                            case 17:{
                                right16++;
                            }break;
                            case 18:{
                                right17++;
                            }break;
                            case 19:{
                                right18++;
                            }break;
                            case 20:{
                                right19++;
                            }break;
                            case 21:{
                                right20++;
                            }break;
                            case 22:{
                                right21++;
                            }break;
                            case 23:{
                                right22++;
                            }break;
                        }
                    }
                    else {
                        bgPixmapInnerTableTapNot.setColor(Color.RED);
                        if (coin>=200 ){
                            coin-=200;
                        }
                        else {
                            moneyError();
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    long time = System.currentTimeMillis();
                                    while (System.currentTimeMillis()<time+1200){}
                                    Gdx.app.postRunnable(new Runnable() {
                                        @Override
                                        public void run() {
                                            game.setScreen(new Main(game,0,0));
                                        }
                                    });
                                }
                            }).start();
                        }
                    }
                    bgPixmapInnerTableTapNot.fill();
                    TextureRegionDrawable textureRegionDrawableInnerTapNot = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmapInnerTableTapNot)));
                    innerTable2.setBackground(textureRegionDrawableInnerTapNot);

                    rightTekhnika();

                }
                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });

            final Table innerTable3 = new Table();
            innerTable3.setBackground(textureRegionDrawableInner);
            innerTable3.add(new Label(labelInstr3,labelStyle)).size(150,20).align(Align.center);
            innerTable3.row();
            innerTable3.add(new Image(texture3)).size(300,220).padBottom(20);

            innerTable3.addListener(new InputListener(){
                @Override
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    Pixmap bgPixmapInnerTableTapNot = new Pixmap(1,1,Pixmap.Format.RGB565);
                    if (c==1){
                        bgPixmapInnerTableTapNot.setColor(Color.GREEN);
                        switch (quest) {
                            case 1: {
                                right++;
                            }break;
                            case 2: {
                                right1++;
                            }break;
                            case 3:{
                                right2++;
                            }break;
                            case 4:{
                                right3++;
                            }break;
                            case 5:{
                                right4++;
                            }break;
                            case 6:{
                                right5++;
                            }break;
                            case 7:{
                                right6++;
                            }break;
                            case 8:{
                                right7++;
                            }break;
                            case 9:{
                                right8++;
                            }break;
                            case 10:{
                                right9++;
                            }break;
                            case 11:{
                                right10++;
                            }break;
                            case 12:{
                                right11++;
                            }break;
                            case 13:{
                                right12++;
                            }break;
                            case 14:{
                                right13++;
                            }break;
                            case 15:{
                                right14++;
                            }break;
                            case 16:{
                                right15++;
                            }break;
                            case 17:{
                                right16++;
                            }break;
                            case 18:{
                                right17++;
                            }break;
                            case 19:{
                                right18++;
                            }break;
                            case 20:{
                                right19++;
                            }break;
                            case 21:{
                                right20++;
                            }break;
                            case 22:{
                                right21++;
                            }break;
                            case 23:{
                                right22++;
                            }break;
                        }
                    }
                    else {
                        bgPixmapInnerTableTapNot.setColor(Color.RED);
                        if (coin>=200 ){
                            coin-=200;
                        }
                        else {
                            moneyError();
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    long time = System.currentTimeMillis();
                                    while (System.currentTimeMillis()<time+1200){}
                                    Gdx.app.postRunnable(new Runnable() {
                                        @Override
                                        public void run() {
                                            game.setScreen(new Main(game,0,0));
                                        }
                                    });
                                }
                            }).start();
                        }
                    }
                    bgPixmapInnerTableTapNot.fill();
                    TextureRegionDrawable textureRegionDrawableInnerTapNot = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmapInnerTableTapNot)));
                    innerTable3.setBackground(textureRegionDrawableInnerTapNot);

                    rightTekhnika();

                }
                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });

            final Table innerTable4 = new Table();
            innerTable4.setBackground(textureRegionDrawableInner);
            innerTable4.add(new Label(labelInstr4,labelStyle)).size(150,20).align(Align.center);
            innerTable4.row();
            innerTable4.add(new Image(texture4)).size(300,220).padBottom(20);

            innerTable4.addListener(new InputListener(){
                @Override
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    Pixmap bgPixmapInnerTableTapNot = new Pixmap(1,1,Pixmap.Format.RGB565);
                    if (d==1){
                        bgPixmapInnerTableTapNot.setColor(Color.GREEN);
                        switch (quest) {
                            case 1: {
                                right++;
                            }break;
                            case 2: {
                                right1++;
                            }break;
                            case 3:{
                                right2++;
                            }break;
                            case 4:{
                                right3++;
                            }break;
                            case 5:{
                                right4++;
                            }break;
                            case 6:{
                                right5++;
                            }break;
                            case 7:{
                                right6++;
                            }break;
                            case 8:{
                                right7++;
                            }break;
                            case 9:{
                                right8++;
                            }break;
                            case 10:{
                                right9++;
                            }break;
                            case 11:{
                                right10++;
                            }break;
                            case 12:{
                                right11++;
                            }break;
                            case 13:{
                                right12++;
                            }break;
                            case 14:{
                                right13++;
                            }break;
                            case 15:{
                                right14++;
                            }break;
                            case 16:{
                                right15++;
                            }break;
                            case 17:{
                                right16++;
                            }break;
                            case 18:{
                                right17++;
                            }break;
                            case 19:{
                                right18++;
                            }break;
                            case 20:{
                                right19++;
                            }break;
                            case 21:{
                                right20++;
                            }break;
                            case 22:{
                                right21++;
                            }break;
                            case 23:{
                                right22++;
                            }break;
                        }
                    }
                    else {
                        bgPixmapInnerTableTapNot.setColor(Color.RED);
                        if (coin>=200 ){
                            coin-=200;
                        }
                        else {
                            moneyError();
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    long time = System.currentTimeMillis();
                                    while (System.currentTimeMillis()<time+1200){}
                                    Gdx.app.postRunnable(new Runnable() {
                                        @Override
                                        public void run() {
                                            game.setScreen(new Main(game,0,0));
                                        }
                                    });
                                }
                            }).start();
                        }
                    }
                    bgPixmapInnerTableTapNot.fill();
                    TextureRegionDrawable textureRegionDrawableInnerTapNot = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmapInnerTableTapNot)));
                    innerTable4.setBackground(textureRegionDrawableInnerTapNot);

                    rightTekhnika();

                }
                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });

            buidTownButtonInventory.add(btnClose).size(30,30).right();
            buidTownButtonInventory.row();
            buidTownButtonInventory.add(innerTable).size(300,300).pad(10).left();
            buidTownButtonInventory.add(innerTable2).size(300,300).right();
            buidTownButtonInventory.row();
            buidTownButtonInventory.add(innerTable3).size(300,300).pad(10).left();
            buidTownButtonInventory.add(innerTable4).size(300,300).right();

            stage.addActor(buidTownButtonInventory);
    //    }
  //      else {
  //          buidTownButtonInventory.remove();
        //    close=1;
  //      }

      /*  if (close==1){
            hasBeenClick =false;
            close =0;
        }*/
    }

    public void dialogAlert (Label text, String title){

        Gdx.input.setInputProcessor(stage);
        Skin uiSkin = new Skin(Gdx.files.internal("skin/1.json"));
        final Dialog dialog = new Dialog(title, uiSkin, "default") {

            public void result(Object obj) {
                if(obj.equals(true)){
                    hide();
                    cancel();
                    remove();

                }
            }
        };

        dialog.text(text);
        TextButton btnYes = new TextButton(" Ok ", uiSkin);
        dialog.button(btnYes,true);
        dialog.setModal(true);
        dialog.setMovable(false);
        dialog.setResizable(false);
        dialog.show(stage);
    }

    public void dialogAlertMoney (Label text, String title){

        Gdx.input.setInputProcessor(stage);
        Skin uiSkin = new Skin(Gdx.files.internal("skin/1.json"));
        final Dialog dialog = new Dialog(title, uiSkin, "default") {

            public void result(Object obj) {
                if(obj.equals(true)){
                    hide();
                    cancel();
                    remove();

                }
            }
        };

        dialog.text(text);
        dialog.setModal(true);
        dialog.setMovable(false);
        dialog.setResizable(false);
        dialog.show(stage);
    }

    public void moneyError (){
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font1;
        font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Label label = new Label("Закончились деньги на строительство. Уровень не пройден!", labelStyle);
        buidTownButtonInventory.remove();
        dialogAlertMoney(label, "Уровень не пройден!");
    }

    public void rightTekhnika(){
        if (right==3){
            if (coin>=37000 ){
                coin-=37000;
            }
            else {
                moneyError();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long time = System.currentTimeMillis();
                        while (System.currentTimeMillis()<time+1200){}
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                game.setScreen(new Main(game,0,0));
                            }
                        });
                    }
                }).start();
            }

        }
        if (right1==2){
            if (coin>=37000 ){
                coin-=37000;
            }
            else {
                moneyError();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long time = System.currentTimeMillis();
                        while (System.currentTimeMillis()<time+1200){}
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                game.setScreen(new Main(game,0,0));
                            }
                        });
                    }
                }).start();
            }
        }
        if (right2==4){
            if (coin>=37000 ){
                coin-=37000;
            }
            else {
                moneyError();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long time = System.currentTimeMillis();
                        while (System.currentTimeMillis()<time+1200){}
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                game.setScreen(new Main(game,0,0));
                            }
                        });
                    }
                }).start();
            }
        }
        if (right3==3){
            if (coin>=37000 ){
                coin-=37000;
            }
            else {
                moneyError();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long time = System.currentTimeMillis();
                        while (System.currentTimeMillis()<time+1200){}
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                game.setScreen(new Main(game,0,0));
                            }
                        });
                    }
                }).start();
            }
        }
        if (right4==3){
            if (coin>=37000){
                coin-=37000;
            }
            else {
                moneyError();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long time = System.currentTimeMillis();
                        while (System.currentTimeMillis()<time+1200){}
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                game.setScreen(new Main(game,0,0));
                            }
                        });
                    }
                }).start();
            }
        }
        if (right5==2){
            if (coin>=37000 ){
                coin-=37000;
            }
            else {
                moneyError();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long time = System.currentTimeMillis();
                        while (System.currentTimeMillis()<time+1200){}
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                game.setScreen(new Main(game,0,0));
                            }
                        });
                    }
                }).start();
            }
        }
        if (right6==2){
            if (coin>=37000 ){
                coin-=37000;
            }
            else {
                moneyError();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long time = System.currentTimeMillis();
                        while (System.currentTimeMillis()<time+1200){}
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                game.setScreen(new Main(game,0,0));
                            }
                        });
                    }
                }).start();
            }
        }
        if (right7==3){
            if (coin>=37000 ){
                coin-=37000;
            }
            else {
                moneyError();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long time = System.currentTimeMillis();
                        while (System.currentTimeMillis()<time+1200){}
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                game.setScreen(new Main(game,0,0));
                            }
                        });
                    }
                }).start();
            }
        }
        if (right8==2){
            if (coin>=37000 ){
                coin-=37000;
            }
            else {
                moneyError();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long time = System.currentTimeMillis();
                        while (System.currentTimeMillis()<time+1200){}
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                game.setScreen(new Main(game,0,0));
                            }
                        });
                    }
                }).start();
            }
        }
        if (right9==4){
            if (coin>=37000 ){
                coin-=37000;
            }
            else {
                moneyError();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long time = System.currentTimeMillis();
                        while (System.currentTimeMillis()<time+1200){}
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                game.setScreen(new Main(game,0,0));
                            }
                        });
                    }
                }).start();
            }
        }
        if (right10==1){
            if (coin>=37000 ){
                coin-=37000;
            }
            else {
                moneyError();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long time = System.currentTimeMillis();
                        while (System.currentTimeMillis()<time+1200){}
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                game.setScreen(new Main(game,0,0));
                            }
                        });
                    }
                }).start();
            }
        }

        if (right11==1){
            if (coin>=37000 ){
                coin-=37000;
            }
            else {
                moneyError();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long time = System.currentTimeMillis();
                        while (System.currentTimeMillis()<time+1200){}
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                game.setScreen(new Main(game,0,0));
                            }
                        });
                    }
                }).start();
            }
        }

        if (right12==1){
            if (coin>=37000 ){
                coin-=37000;
            }
            else {
                moneyError();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long time = System.currentTimeMillis();
                        while (System.currentTimeMillis()<time+1200){}
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                game.setScreen(new Main(game,0,0));
                            }
                        });
                    }
                }).start();
            }
        }

        if (right13==1){
            if (coin>=37000 ){
                coin-=37000;
            }
            else {
                moneyError();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long time = System.currentTimeMillis();
                        while (System.currentTimeMillis()<time+1200){}
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                game.setScreen(new Main(game,0,0));
                            }
                        });
                    }
                }).start();
            }
        }

        if (right14==1){
            if (coin>=37000 ){
                coin-=37000;
            }
            else {
                moneyError();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long time = System.currentTimeMillis();
                        while (System.currentTimeMillis()<time+1200){}
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                game.setScreen(new Main(game,0,0));
                            }
                        });
                    }
                }).start();
            }
        }

        if (right15==2){
            if (coin>=37000 ){
                coin-=37000;
            }
            else {
                moneyError();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long time = System.currentTimeMillis();
                        while (System.currentTimeMillis()<time+1200){}
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                game.setScreen(new Main(game,0,0));
                            }
                        });
                    }
                }).start();
            }
        }
        if (right16==3){
            if (coin>=37000 ){
                coin-=37000;
            }
            else {
                moneyError();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long time = System.currentTimeMillis();
                        while (System.currentTimeMillis()<time+1200){}
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                game.setScreen(new Main(game,0,0));
                            }
                        });
                    }
                }).start();
            }
        }

        if (right17==3){
            if (coin>=37000 ){
                coin-=37000;
            }
            else {
                moneyError();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long time = System.currentTimeMillis();
                        while (System.currentTimeMillis()<time+1200){}
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                game.setScreen(new Main(game,0,0));
                            }
                        });
                    }
                }).start();
            }
        }

        if (right18==3){
            if (coin>=37000 ){
                coin-=37000;
            }
            else {
                moneyError();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long time = System.currentTimeMillis();
                        while (System.currentTimeMillis()<time+1200){}
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                game.setScreen(new Main(game,0,0));
                            }
                        });
                    }
                }).start();
            }
        }

        if (right19==2){
            if (coin>=37000 ){
                coin-=37000;
            }
            else {
                moneyError();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long time = System.currentTimeMillis();
                        while (System.currentTimeMillis()<time+1200){}
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                game.setScreen(new Main(game,0,0));
                            }
                        });
                    }
                }).start();
            }
        }

        if (right20==2){
            if (coin>=37000 ){
                coin-=37000;
            }
            else {
                moneyError();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long time = System.currentTimeMillis();
                        while (System.currentTimeMillis()<time+1200){}
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                game.setScreen(new Main(game,0,0));
                            }
                        });
                    }
                }).start();
            }
        }

        if (right21==2){
            if (coin>=37000 ){
                coin-=37000;
            }
            else {
                moneyError();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long time = System.currentTimeMillis();
                        while (System.currentTimeMillis()<time+1200){}
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                game.setScreen(new Main(game,0,0));
                            }
                        });
                    }
                }).start();
            }
        }

        if (right22==2){
            if (coin>=37000 ){
                coin-=37000;
            }
            else {
                moneyError();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long time = System.currentTimeMillis();
                        while (System.currentTimeMillis()<time+1200){}
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                game.setScreen(new Main(game,0,0));
                            }
                        });
                    }
                }).start();
            }
        }

    }

}
