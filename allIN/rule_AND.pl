%SKM_Park
shopping(skm_park).

%高雄流行音樂中心
literature_art(music_center).
taking_picture(music_center).

%蓮池潭風景區
nature(lianchihtan).
taking_picture(lianchihtan).

%駁二藝術特區
shopping(pier2_art_center).
literature_art(pier2_art_center).
taking_picture(pier2_art_center).

%打狗英國領事館文化園區
cultural_history(british_consulate).

%高雄市立圖書館總館
literature_art(main_public_library).
taking_picture(main_public_library).

%義大世界
shopping(eda_theme_park).

%旗津海水浴場
nature(cijin_beach).
taking_picture(cijin_beach).

%愛河
nature(love_river).
taking_picture(love_river).

%棧貳庫
shopping(kw2).
nature(kw2).
literature_art(kw2).
taking_picture(kw2).

%興達港
nature(xing_da_harbor).
taking_picture(xing_da_harbor).

%統一夢時代購物中心
shopping(dream_mall).

%壽山動物園
animal(shou_shan_zoo).

%哈瑪星鐵道文化園區
cultural_history(hamasen).
taking_picture(hamasen).

%西子灣觀景台
nature(xiziwan).
taking_picture(xiziwan).

%觀音山風景區
nature(guanyin_mountain).

%橋頭糖廠
cultural_history(ciaotou_sugar_refinery).
taking_picture(ciaotou_sugar_refinery).

%崗山之眼
nature(siaogangshan_skywalk).
taking_picture(siaogangshan_skywalk).

%高雄市立美術館
literature_art(museum_of_fine_arts).
taking_picture(museum_of_fine_arts).

%孔廟
cultural_history(confucian_temple).

%鳳山龍山寺
cultural_history(fongshan_longshan_buddhist_temple).

%飛行劇院
technology(i_ride).

%高雄市天文教育館
technology(astronomical_museum).

%高雄巨蛋
shopping(arena).

%空軍軍史館
battlefield(air_force_museum).

%陸軍官校
battlefield(military_academy).

%澄清湖風景區
nature(chengcing_lake).
taking_picture(chengcing_lake).

%高雄市立電影館
literature_art(film_archive).

%國立科學工藝博物館
technology(national_science_and_technology_museum).

%台灣螺絲博物館
technology(fastener_museum).

spots_1(S) :- shopping(S), select(shopping).
spots_2(S) :- technology(S), select(technology).
spots_3(S) :- nature(S), select(nature).
spots_4(S) :- literature_art(S), select(literature_art).
spots_5(S) :- animal(S), select(animal).
spots_6(S) :- cultural_history(S), select(cultural_history).
spots_7(S) :- battlefield(S), select(battlefield).
spots_8(S) :- taking_picture(S), select(taking_picture).
