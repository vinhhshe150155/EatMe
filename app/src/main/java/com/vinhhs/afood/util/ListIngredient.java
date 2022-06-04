package com.vinhhs.afood.util;

import com.vinhhs.afood.model.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListIngredient {
    public static List<Ingredient> getAllIngredient() {
        String str = "5 spice powder;1002002\n" +
                "acorn squash;11482\n" +
                "adobo sauce;6979\n" +
                "agave nectar;19912\n" +
                "ahi tuna;15117\n" +
                "alfredo pasta sauce;93606\n" +
                "almond extract;1002050\n" +
                "almond flour;93740\n" +
                "almond milk;93607\n" +
                "almonds;12061\n" +
                "amaretto;10014534\n" +
                "ancho chiles;10211962\n" +
                "anchovies;15001\n" +
                "andouille sausage;7064\n" +
                "angel food cake mix;18087\n" +
                "angel hair pasta;10020420\n" +
                "angostura bitters;93653\n" +
                "apple;9003\n" +
                "apple butter spread;19294\n" +
                "apple cider;1009016\n" +
                "apple juice;9016\n" +
                "apple pie spice;1042035\n" +
                "apricot preserves;19719\n" +
                "apricots;9021\n" +
                "arborio rice;10020052\n" +
                "arrowroot powder;20003\n" +
                "artichoke heart quarters;93828\n" +
                "artichokes;11007\n" +
                "arugula;11959\n" +
                "asafoetida;1032035\n" +
                "asafoetida powder;0\n" +
                "asiago cheese;1001033\n" +
                "asian pear;9252\n" +
                "asparagus spears;11011\n" +
                "avocado;9037\n" +
                "avocado oil;4581\n" +
                "baby bell peppers;10311821\n" +
                "baby bok choy;93636\n" +
                "baby carrots;11960\n" +
                "baby corn;10011168\n" +
                "baby spinach leaves;11457\n" +
                "baby-back ribs;10010204\n" +
                "baby-back ribs;10192\n" +
                "bacon;10123\n" +
                "bacon fat;4609\n" +
                "baguette;18033\n" +
                "baking bar;19078\n" +
                "baking powder;18371\n" +
                "baking soda;18372\n" +
                "balsamic glaze;98998\n" +
                "balsamic vinegar;2069\n" +
                "bamboo shoots;11028\n" +
                "banana;9040\n" +
                "basmati rice;10020444\n" +
                "bay leaves;2004\n" +
                "bbq sauce;6150\n" +
                "beans;16069\n" +
                "beef;23572\n" +
                "beef brisket;13023\n" +
                "beef broth;6008\n" +
                "beef chuck roast;13786\n" +
                "beef stock;6170\n" +
                "beef tenderloin;13926\n" +
                "beer;14003\n" +
                "beer;14006\n" +
                "beets;11080\n" +
                "bell pepper;10211821\n" +
                "berries;1009054\n" +
                "biscuit mix;18010\n" +
                "biscuits;18009\n" +
                "bittersweet chocolate;19903\n" +
                "black bean sauce;99210\n" +
                "black beans;16015\n" +
                "black olives;1059195\n" +
                "black pepper;1002030\n" +
                "black sesame seeds;10012023\n" +
                "blackberries;9042\n" +
                "blanched almonds;12062\n" +
                "blood orange;1009200\n" +
                "blue cheese;1004\n" +
                "blueberries;9050\n" +
                "bok choy;11116\n" +
                "boneless skinless chicken breast;1055062\n" +
                "bourbon;10014037\n" +
                "brandy;10114037\n" +
                "bread;18064\n" +
                "bread flour;10120129\n" +
                "breakfast links;7919\n" +
                "brie;1006\n" +
                "broccoli;11090\n" +
                "broccoli florets;10011090\n" +
                "brown rice;20040\n" +
                "brown rice flour;20090\n" +
                "brown sugar;19334\n" +
                "brownie mix;18632\n" +
                "brussel sprouts;11098\n" +
                "bulgur;20012\n" +
                "butter;1001\n" +
                "butterhead lettuce;11250\n" +
                "buttermilk;1230\n" +
                "butternut squash;11485\n" +
                "butterscotch chips;19070\n" +
                "cabbage;11109\n" +
                "caesar dressing;43015\n" +
                "cajun seasoning;1032028\n" +
                "cake flour;10020129\n" +
                "candy canes;93759\n" +
                "candy coating;98857\n" +
                "candy melts;93775\n" +
                "canned black beans;16018\n" +
                "canned diced tomatoes;11531\n" +
                "canned garbanzo beans;16058\n" +
                "canned green chiles;11980\n" +
                "canned kidney beans;16034\n" +
                "canned mushrooms;11264\n" +
                "canned pinto beans;16044\n" +
                "canned red kidney beans;10016034\n" +
                "canned tomatoes;10011693\n" +
                "canned tuna;10115121\n" +
                "canned white beans;16051\n" +
                "canned white cannellini beans;10016051\n" +
                "cannellini beans;10716050\n" +
                "cantaloupe;9181\n" +
                "capers;2054\n" +
                "caramel sauce;19364\n" +
                "caramels;19074\n" +
                "caraway seed;2005\n" +
                "cardamom;2006\n" +
                "cardamom pods;1002006\n" +
                "carp;15008\n" +
                "carrots;11124\n" +
                "cat fish filets;15010\n" +
                "cauliflower;11135\n" +
                "cauliflower florets;10011135\n" +
                "cauliflower rice;10111135\n" +
                "celery;11143\n" +
                "celery ribs;10111143\n" +
                "celery root;11141\n" +
                "celery salt;1052047\n" +
                "celery seed;2007\n" +
                "cereal;8029\n" +
                "champagne;10043155\n" +
                "chana dal;99236\n" +
                "cheddar;1009\n" +
                "cheese;1041009\n" +
                "cheese curds;98921\n" +
                "cheese dip;1188\n" +
                "cheese soup;6038\n" +
                "cheese tortellini;10093727\n" +
                "cherry;9070\n" +
                "cherry pie filling;19314\n" +
                "cherry tomatoes;10311529\n" +
                "chestnuts;12098\n" +
                "chia seeds;12006\n" +
                "chicken base;6080\n" +
                "chicken bouillon;6480\n" +
                "chicken bouillon granules;1006080\n" +
                "chicken breasts;5062\n" +
                "chicken broth;6194\n" +
                "chicken drumsticks;5066\n" +
                "chicken legs;5075\n" +
                "chicken pieces;1005006\n" +
                "chicken sausage;93668\n" +
                "chicken stock;6172\n" +
                "chicken tenders;1015062\n" +
                "chicken thighs;5091\n" +
                "chicken wings;5100\n" +
                "chickpea;16057\n" +
                "chile garlic sauce;93749\n" +
                "chili paste;6973\n" +
                "chili peppers;11962\n" +
                "chili powder;2009\n" +
                "chili sauce;6972\n" +
                "chipotle chiles in adobo;11632\n" +
                "chipotle chilies;98839\n" +
                "chipotle peppers in adobo;99223\n" +
                "chive & onion cream cheese spread;93748\n" +
                "chocolate;19081\n" +
                "chocolate chip cookies;28027\n" +
                "chocolate chunks;10419903\n" +
                "chocolate ice cream;19270\n" +
                "chocolate milk;1102\n" +
                "chocolate sandwich cookies;18166\n" +
                "chocolate syrup;14181\n" +
                "chocolate wafer cookies;10118157\n" +
                "chorizo sausage;7019\n" +
                "cider vinegar;2048\n" +
                "cilantro;11165\n" +
                "cinnamon roll;99020\n" +
                "cinnamon stick;1002010\n" +
                "cinnamon sugar;10219335\n" +
                "cinnamon swirl bread;18047\n" +
                "clam juice;14187\n" +
                "clams;15157\n" +
                "clarified butter;93632\n" +
                "clove;1002011\n" +
                "coarse salt;1002047\n" +
                "coarsely ground pepper;2035\n" +
                "cocoa nibs;98846\n" +
                "cocoa powder;19165\n" +
                "coconut;12104\n" +
                "coconut aminos;98929\n" +
                "coconut butter;93746\n" +
                "coconut cream;12115\n" +
                "coconut extract;1032050\n" +
                "coconut flour;93747\n" +
                "coconut milk;12118\n" +
                "coconut oil;4047\n" +
                "coconut water;12119\n" +
                "cod;15015\n" +
                "coffee;14209\n" +
                "cognac;10414037\n" +
                "cola;14400\n" +
                "colby jack;1011\n" +
                "collard greens;11161\n" +
                "condensed cream of celery soup;6010\n" +
                "condensed cream of mushroom soup;6147\n" +
                "confectioner's swerve;99084\n" +
                "cooked bacon;10862\n" +
                "cooked brown rice;20041\n" +
                "cooked chicken breast;5064\n" +
                "cooked ham;10802\n" +
                "cooked long grain rice;10220445\n" +
                "cooked pasta;20421\n" +
                "cooked polenta;1008166\n" +
                "cooked quinoa;20137\n" +
                "cooked wild rice;20088\n" +
                "cookies;10118192\n" +
                "coriander;2012\n" +
                "corn;11168\n" +
                "corn bread mix;18022\n" +
                "corn chips;19003\n" +
                "corn flakes cereal;8020\n" +
                "corn flour;20019\n" +
                "corn kernels;11172\n" +
                "corn oil;42289\n" +
                "corn tortillas;18363\n" +
                "cornbread;18023\n" +
                "corned beef;13346\n" +
                "cornish hens;5307\n" +
                "cornmeal;35137\n" +
                "cornstarch;20027\n" +
                "cotija cheese;1001019\n" +
                "cottage cheese;1012\n" +
                "country bread;10018029\n" +
                "courgettes;11477\n" +
                "couscous;20028\n" +
                "cow pea;16063\n" +
                "crabmeat;10015136\n" +
                "cracked pepper;2030\n" +
                "cranberries;9078\n" +
                "cranberry juice;43382\n" +
                "cream;1053\n" +
                "cream cheese;1017\n" +
                "cream cheese block;1186\n" +
                "cream of chicken soup;6016\n" +
                "cream of tartar;18373\n" +
                "creamed corn;11174\n" +
                "creamy peanut butter;10116098\n" +
                "creme fraiche;1001056\n" +
                "cremini mushrooms;11266\n" +
                "creole seasoning;1002031\n" +
                "crisp rice cereal;8066\n" +
                "croutons;18242\n" +
                "crystallized ginger;93751\n" +
                "cucumber;11206\n" +
                "cumin seeds;2014\n" +
                "cup cake;18139\n" +
                "currants;9085\n" +
                "curry leaves;93604\n" +
                "dairy free milk;10016223\n" +
                "dark brown sugar;10019334\n" +
                "dark chocolate;19904\n" +
                "dark chocolate candy bars;10019904\n" +
                "dark chocolate chips;10019071\n" +
                "dark sesame oil;1004058\n" +
                "dates;9087\n" +
                "deep dish pie crust;18945\n" +
                "deli ham;10010151\n" +
                "deli turkey;7259\n" +
                "dessert oats;8121\n" +
                "dessert wine;10014057\n" +
                "diced ham;99186\n" +
                "diet pop;14146\n" +
                "dijon mustard;1002046\n" +
                "dill;2045\n" +
                "dill pickles;10011937\n" +
                "hot dog;21118\n" +
                "double cream;1011053\n" +
                "dried apricots;9032\n" +
                "dried basil;2003\n" +
                "dried cherries;93822\n" +
                "dried chorizo;99233\n" +
                "dried cranberries;9079\n" +
                "dried dill;2017\n" +
                "dried onion;11284\n" +
                "dried porcini mushrooms;10011268\n" +
                "dried rubbed sage;1002038\n" +
                "dried thyme;2042\n" +
                "dried tomatoes;11955\n" +
                "dry bread crumbs;18079\n" +
                "dry milk;1090\n" +
                "dry mustard;1002024\n" +
                "dry red wine;14097\n" +
                "dry roasted peanuts;16090\n" +
                "duck fat;4574\n" +
                "dutch process cocoa powder;10019165\n" +
                "edamame;11212\n" +
                "egg substitute;1226\n" +
                "egg vermicelli;20409\n" +
                "egg whites;1124\n" +
                "egg yolk;1125\n" +
                "eggnog;1057\n" +
                "eggplant;11209\n" +
                "elbow macaroni;10120499\n" +
                "enchilada sauce;6599\n" +
                "english cucumber;10111205\n" +
                "english muffin;18439\n" +
                "erythritol;98887\n" +
                "escarole;11213\n" +
                "espresso;14210\n" +
                "evaporated milk;1214\n" +
                "extra firm tofu;16163\n" +
                "extra virgin olive oil;1034053\n" +
                "farfalle;10120420\n" +
                "farro;10020005\n" +
                "fat free mayo;42193\n" +
                "fat-free less-sodium chicken broth;6984\n" +
                "fennel;11957\n" +
                "fennel seeds;2018\n" +
                "fenugreek leaf;98963\n" +
                "fenugreek seeds;2019\n" +
                "feta cheese;1019\n" +
                "fettuccine;10020409\n" +
                "fire roasted tomatoes;98849\n" +
                "fish;10115261\n" +
                "fish sauce;6179\n" +
                "fish stock;6963\n" +
                "flank steak;23657\n" +
                "flax seeds;10012220\n" +
                "fleur de sel;1022047\n" +
                "flour;20081\n" +
                "flour tortillas;10218364\n" +
                "fontina cheese;1020\n" +
                "food dye;10711111\n" +
                "frank's redhot sauce;98878\n" +
                "free range eggs;1123\n" +
                "french bread;18029\n" +
                "fresh basil;2044\n" +
                "fresh bean sprouts;11043\n" +
                "fresh chives;11156\n" +
                "fresh corn;11167\n" +
                "fresh corn kernels;10011167\n" +
                "fresh figs;9089\n" +
                "fresh fruit;9431\n" +
                "fresh herbs;10111297\n" +
                "fresh mint;2064\n" +
                "fresh mozzarella;1026\n" +
                "fresh rosemary;2063\n" +
                "fresh thyme leaves;2049\n" +
                "fried onions;93709\n" +
                "frosting;19230\n" +
                "froyo bars;93629\n" +
                "frozen corn;11913\n" +
                "frozen spinach;11463\n" +
                "fudge;19100\n" +
                "fudge topping;10019348\n" +
                "fun size almond joy bar;19065\n" +
                "garam masala;93663\n" +
                "garbanzo bean flour;16157\n" +
                "garlic;11215\n" +
                "garlic paste;10111215\n" +
                "garlic powder;1022020\n" +
                "garlic powder;2020\n" +
                "garlic salt;1062047\n" +
                "gelatin;19177\n" +
                "gf chocolate cake mix;99040\n" +
                "gin;10514037\n" +
                "ginger;11216\n" +
                "ginger ale;14136\n" +
                "ginger paste;93754\n" +
                "ginger-garlic paste;10093754\n" +
                "gingersnap cookies;18172\n" +
                "gnocchi;98853\n" +
                "goat cheese;1159\n" +
                "golden raisins;9297\n" +
                "gorgonzola;1011004\n" +
                "gouda cheese;1022\n" +
                "graham cracker crumbs;10018617\n" +
                "graham cracker pie crust;18942\n" +
                "graham crackers;18617\n" +
                "grain blend;10020088\n" +
                "grand marnier;10314534\n" +
                "granny smith apples;1089003\n" +
                "granola;8212\n" +
                "granulated garlic;1002020\n" +
                "grape tomatoes;10111529\n" +
                "grapefruit;9112\n" +
                "grapeseed oil;4517\n" +
                "gravy;6997\n" +
                "great northern beans;16025\n" +
                "greek yogurt;1256\n" +
                "green beans;11052\n" +
                "green bell pepper;11333\n" +
                "green chili pepper;31015\n" +
                "green food coloring;1441111\n" +
                "green grapes;1019132\n" +
                "green olives;1029195\n" +
                "green onions;11291\n" +
                "greens;21052\n" +
                "grill cheese;10093624\n" +
                "grill seasoning;1022034\n" +
                "ground allspice;2001\n" +
                "ground ancho chili;1022009\n" +
                "ground beef;10023572\n" +
                "ground chicken;5332\n" +
                "ground chipotle chile pepper;1052009\n" +
                "ground cinnamon;1012010\n" +
                "ground cinnamon;2010\n" +
                "ground cloves;2011\n" +
                "ground coriander seeds;1002013\n" +
                "ground cumin;1002014\n" +
                "ground flaxseed;12220\n" +
                "ground ginger;2021\n" +
                "ground lamb;17224\n" +
                "ground mace;2022\n" +
                "ground nutmeg;2025\n" +
                "ground pork;10219\n" +
                "ground pork sausage;7063\n" +
                "ground veal;17142\n" +
                "gruyere;1023\n" +
                "guacamole;1009037\n" +
                "half n half;1049\n" +
                "halibut fillet;15036\n" +
                "ham;10151\n" +
                "hamburger buns;18350\n" +
                "hard cooked eggs;1129\n" +
                "harissa;1006972\n" +
                "hash brown potatoes;11390\n" +
                "hazelnuts;12120\n" +
                "healthy request cream of celery soup;6987\n" +
                "hemp seeds;93602\n" +
                "herbes de provence;1012042\n" +
                "herbs;1002044\n" +
                "hershey's kisses brand milk chocolates;93743\n" +
                "hoisin sauce;6175\n" +
                "honey mustard;99227\n" +
                "horseradish;1002055\n" +
                "hot sauce;6168\n" +
                "hummus;16158\n" +
                "ice;10014412\n" +
                "ice cream;19095\n" +
                "instant chocolate pudding mix;19184\n" +
                "instant coffee powder;14214\n" +
                "instant espresso powder;10014214\n" +
                "instant lemon pudding mix;19332\n" +
                "instant yeast;10118375\n" +
                "irish cream;93764\n" +
                "italian bread;10028033\n" +
                "italian cheese blend;93651\n" +
                "italian sausages;7036\n" +
                "italian seasoning;1022027\n" +
                "jaggery;99002\n" +
                "jalapeno;11979\n" +
                "jasmine rice;10120444\n" +
                "jelly;19297\n" +
                "jicama;11603\n" +
                "jimmies;93645\n" +
                "juice;1019016\n" +
                "jumbo shell pasta;10520420\n" +
                "kaffir lime leaves;93633\n" +
                "kahlua;93716\n" +
                "kalamata olives;1009195\n" +
                "kale;11233\n" +
                "ketchup;11935\n" +
                "kitchen bouquet;93768\n" +
                "kiwis;9148\n" +
                "kosher salt;1082047\n" +
                "ladyfingers;18423\n" +
                "lamb;10017224\n" +
                "lasagna noodles;10620420\n" +
                "lb cake;18133\n" +
                "lean ground beef;23557\n" +
                "lean ground turkey;5662\n" +
                "lean pork tenderloin;10060\n" +
                "leeks;11246\n" +
                "leg of lamb;17013\n" +
                "lemon;9150\n" +
                "lemon curd;93834\n" +
                "lemon extract;12311111\n" +
                "lemon juice;9152\n" +
                "lemon peel;9156\n" +
                "lemon pepper;1012030\n" +
                "lemon wedges;1029150\n" +
                "lemongrass;11972\n" +
                "lettuce;11252\n" +
                "lettuce leaves;93623\n" +
                "light butter;4602\n" +
                "light coconut milk;99009\n" +
                "light corn syrup;19350\n" +
                "light cream cheese;43274\n" +
                "light mayonnaise;4641\n" +
                "light olive oil;1054053\n" +
                "light soy sauce;10216124\n" +
                "lime;9159\n" +
                "lime juice;9160\n" +
                "lime wedges;1029159\n" +
                "lime zest;1009159\n" +
                "linguine;10720420\n" +
                "liquid smoke;93627\n" +
                "liquid stevia;10811111\n" +
                "liquor;14037\n" +
                "live lobster;15147\n" +
                "long-grain rice;10220444\n" +
                "low fat buttermilk;1088\n" +
                "low fat milk;1082\n" +
                "low fat milk;1174\n" +
                "low fat plain yogurt;1117\n" +
                "low fat ricotta cheese;1037\n" +
                "low fat sour cream;1179\n" +
                "low sodium chicken broth;6970\n" +
                "low sodium soy sauce;16424\n" +
                "low-sodium chicken stock;1006970\n" +
                "lower sodium beef broth;10093741\n" +
                "lump crab;10115136\n" +
                "m&m candies;19157\n" +
                "macadamia nuts;12131\n" +
                "macaroni and cheese mix;32004\n" +
                "madras curry powder;2015\n" +
                "malt drink mix;14311\n" +
                "mandarin orange sections;9383\n" +
                "mandarin oranges;9218\n" +
                "mango;9176\n" +
                "maple syrup;19911\n" +
                "maraschino cherries;9328\n" +
                "margarine;4073\n" +
                "marinara sauce;10111549\n" +
                "marjoram;2023\n" +
                "marsala wine;14057\n" +
                "marshmallow fluff;93644\n" +
                "marshmallows;19116\n" +
                "masa harina;20317\n" +
                "mascarpone;93820\n" +
                "mat beans;99144\n" +
                "matcha tea;98932\n" +
                "mayonnaise;4025\n" +
                "meat;1015006\n" +
                "meat;1065062\n" +
                "meatballs;10110219\n" +
                "medjool dates;9421\n" +
                "mexican cream;93772\n" +
                "meyer lemon juice;1009152\n" +
                "milk;1077\n" +
                "milk chocolate chips;10019146\n" +
                "mint chutney;98991\n" +
                "minute rice;20048\n" +
                "miracle whip;4014\n" +
                "mirin;93830\n" +
                "miso;16112\n" +
                "molasses;19304\n" +
                "monterey jack cheese;1001025\n" +
                "mushroom;11260\n" +
                "mussels;15164\n" +
                "mustard;2046\n" +
                "mustard seeds;2024\n" +
                "napa cabbage;11119\n" +
                "navel oranges;9202\n" +
                "nectarine;9191\n" +
                "new potatoes;11352\n" +
                "non-fat greek yogurt;1011256\n" +
                "nonfat cool whip;1200\n" +
                "nonfat milk;1085\n" +
                "nori;11446\n" +
                "nut butter;12195\n" +
                "nut meal;93620\n" +
                "nutella;19125\n" +
                "nutritional yeast;93690\n" +
                "oat flour;20132\n" +
                "oats;8120\n" +
                "oil;4582\n" +
                "oil packed sun dried tomatoes;11956\n" +
                "okra;11278\n" +
                "old bay seasoning;1052034\n" +
                "olive oil;4053\n" +
                "olives;9195\n" +
                "onion;11282\n" +
                "onion powder;2026\n" +
                "onion soup mix;6094\n" +
                "orange;9200\n" +
                "orange bell pepper;10011821\n" +
                "orange juice;9206\n" +
                "orange juice concentrate;9214\n" +
                "orange liqueur;10414534\n" +
                "orange marmalade;19303\n" +
                "orange oil;12511111\n" +
                "orange zest;9216\n" +
                "oregano;2027\n" +
                "oreo cookies;10018166\n" +
                "orzo;10920420\n" +
                "oyster sauce;6176\n" +
                "oysters;15167\n" +
                "palm sugar;93831\n" +
                "pancetta;10410123\n" +
                "paneer;98847\n" +
                "panko;10018079\n" +
                "papaya;9226\n" +
                "paprika;2028\n" +
                "parmigiano reggiano;1033\n" +
                "parsley;11297\n" +
                "parsley flakes;2029\n" +
                "parsnip;11298\n" +
                "part-skim mozzarella cheese;1028\n" +
                "pasta;20420\n" +
                "pasta salad mix;99036\n" +
                "pasta sauce;10011549\n" +
                "pastry flour;10020080\n" +
                "peach;9236\n" +
                "peanut butter;16098\n" +
                "peanut butter chips;93762\n" +
                "peanut butter cups;19150\n" +
                "peanut oil;4042\n" +
                "peanuts;16091\n" +
                "pear liqueur;98988\n" +
                "pearl barley;20005\n" +
                "pearl onions;10111282\n" +
                "peas;11304\n" +
                "pecan;12142\n" +
                "pecan pieces;10012142\n" +
                "pecorino;1038\n" +
                "penne;11120420\n" +
                "peperoncino;11976\n" +
                "pepper jack cheese;1025\n" +
                "peppercorns;1022030\n" +
                "peppermint baking chips;98858\n" +
                "peppermint extract;1022050\n" +
                "pepperoni;7057\n" +
                "peppers;10111333\n" +
                "pesto;93698\n" +
                "pickle relish;11944\n" +
                "pickles;11937\n" +
                "pico de gallo;27027\n" +
                "pie crust;18334\n" +
                "pimento stuffed olives;1049195\n" +
                "pimientos;11943\n" +
                "pine nuts;12147\n" +
                "pineapple;9266\n" +
                "pineapple chunks;1029354\n" +
                "pineapple in juice;9354\n" +
                "pineapple juice;9273\n" +
                "pink himalayan salt;1032047\n" +
                "pinto beans;16043\n" +
                "pistachios;12151\n" +
                "pita;18413\n" +
                "pizza crust;93770\n" +
                "pizza mix;98924\n" +
                "plain greek yogurt;1001256\n" +
                "plain nonfat yogurt;1118\n" +
                "plain yogurt;1001116\n" +
                "plantain;9277\n" +
                "plum;9279\n" +
                "plum tomatoes;10411529\n" +
                "poblano peppers;10011333\n" +
                "polenta;10035137\n" +
                "polish sausage;7059\n" +
                "pomegranate juice;9442\n" +
                "pomegranate molasses;10042040\n" +
                "pomegranate seeds;9286\n" +
                "popcorn;19034\n" +
                "poppy seeds;2033\n" +
                "pork;10010219\n" +
                "Pork & Beans;16009\n" +
                "pork belly;10005\n" +
                "pork butt;10084\n" +
                "pork chops;10010062\n" +
                "pork links;1007063\n" +
                "pork loin chops;10062\n" +
                "pork loin roast;10225\n" +
                "pork roast;10010225\n" +
                "pork shoulder;10072\n" +
                "pork tenderloin;10218\n" +
                "port;10114057\n" +
                "portabella mushrooms;11265\n" +
                "pot roast;23612\n" +
                "potato chips;19411\n" +
                "potato starch;11413\n" +
                "potatoes;11362\n" +
                "poultry seasoning;2034\n" +
                "powdered sugar;19336\n" +
                "pretzel sandwiches;19047\n" +
                "processed american cheese;1253\n" +
                "prosciutto;10010123\n" +
                "provolone cheese;1035\n" +
                "prunes;9291\n" +
                "puff pastry;18337\n" +
                "pumpkin;11422\n" +
                "pumpkin pie filling;11426\n" +
                "pumpkin pie spice;1002035\n" +
                "pumpkin puree;11424\n" +
                "pumpkin seeds;12014\n" +
                "queso fresco;1228\n" +
                "quick cooking oats;8402\n" +
                "quinoa;20035\n" +
                "quinoa flour;93773\n" +
                "radicchio;11952\n" +
                "radishes;11429\n" +
                "raisins;9299\n" +
                "rajma masala;10193663\n" +
                "ramen noodles;6583\n" +
                "ranch dressing;4639\n" +
                "ranch dressing mix;93733\n" +
                "raspberries;9302\n" +
                "raspberry jam;10719297\n" +
                "raw cashews;12087\n" +
                "raw shrimp;15152\n" +
                "ready-to-serve Asian fried rice;93721\n" +
                "real bacon recipe pieces;99229\n" +
                "red apples;1079003\n" +
                "red bell peppers;11821\n" +
                "red cabbage;11112\n" +
                "red chilli;11819\n" +
                "red delicious apples;1059003\n" +
                "red food coloring;1451111\n" +
                "red grapefruit juice;98926\n" +
                "red grapes;9132\n" +
                "red kidney beans;16033\n" +
                "red lentils;10016069\n" +
                "red onion;10011282\n" +
                "red pepper flakes;1032009\n" +
                "red pepper powder;2031\n" +
                "red potatoes;10011355\n" +
                "red velvet cookie;18157\n" +
                "red wine;14096\n" +
                "red wine vinegar;1022068\n" +
                "reduced fat shredded cheddar cheese;1001168\n" +
                "refried beans;16202\n" +
                "refrigerated crescent rolls;93618\n" +
                "refrigerated pizza dough;93610\n" +
                "refrigerated sugar cookie dough;18205\n" +
                "rhubarb;9307\n" +
                "rib tips;98937\n" +
                "rice;20444\n" +
                "rice flour;20061\n" +
                "rice krispies cereal;8065\n" +
                "rice milk;93761\n" +
                "rice noodles;20133\n" +
                "rice paper;10118368\n" +
                "rice syrup;93784\n" +
                "rice vinegar;1022053\n" +
                "rice wine;43479\n" +
                "ricotta salata;1036\n" +
                "ritz crackers;18621\n" +
                "roast beef;93713\n" +
                "roasted chicken;5114\n" +
                "roasted nuts;12135\n" +
                "roasted peanuts;16092\n" +
                "roasted red peppers;11916\n" +
                "roma tomatoes;10211529\n" +
                "romaine lettuce;10111251\n" +
                "root vegetables;10011298\n" +
                "rosemary;2036\n" +
                "rotini pasta;11320420\n" +
                "rotisserie chicken;5348\n" +
                "round steak;23617\n" +
                "rub;1012034\n" +
                "rum extract;12211111\n" +
                "runny honey;19296\n" +
                "russet potatoes;11353\n" +
                "rutabaga;11435\n" +
                "rye bread;18060\n" +
                "rye meal;98905\n" +
                "saffron threads;2037\n" +
                "sage;2038\n" +
                "sage leaves;99226\n" +
                "salad dressing;4114\n" +
                "salami;7071\n" +
                "salmon fillet;15076\n" +
                "salsa;6164\n" +
                "salsa verde;27028\n" +
                "salt;2047\n" +
                "salt and pepper;1102047\n" +
                "salted butter;1001001\n" +
                "saltine crackers;18228\n" +
                "sandwich bun;18353\n" +
                "sauerkraut;11439\n" +
                "sausage;1017063\n" +
                "sausage links;1037063\n" +
                "scotch bonnet chili;10011819\n" +
                "sea salt;1012047\n" +
                "sea scallops;10015172\n" +
                "seasoned bread crumbs;18376\n" +
                "seasoned rice vinegar;1032053\n" +
                "seasoned salt;1042047\n" +
                "seasoning;1042027\n" +
                "seasoning blend;1032027\n" +
                "seeds;93818\n" +
                "self-rising flour;20129\n" +
                "semi sweet chocolate chips;10019903\n" +
                "serrano chile;11977\n" +
                "sesame oil;4058\n" +
                "sesame seed hamburger buns;10018350\n" +
                "sesame seeds;12023\n" +
                "shallot;11677\n" +
                "sharp cheddar cheese;1031009\n" +
                "sheeps milk cheese;1011019\n" +
                "shells;11020420\n" +
                "sherry;10114106\n" +
                "sherry;10214106\n" +
                "sherry vinegar;1012068\n" +
                "shiitake mushroom caps;11238\n" +
                "short grain rice;10120052\n" +
                "short pasta;20499\n" +
                "short ribs;10013149\n" +
                "shortbread cookies;18192\n" +
                "shortcrust pastry;10018338\n" +
                "shortening;4615\n" +
                "shredded cheddar cheese;1001009\n" +
                "shredded cheese;1011026\n" +
                "shredded chicken;1005114\n" +
                "shredded coconut;12108\n" +
                "shredded mexican cheese blend;1001251\n" +
                "shredded mexican cheese blend;1251\n" +
                "shredded mozzarella;1001026\n" +
                "silken tofu;16161\n" +
                "sirloin steak;23625\n" +
                "skim milk ricotta;93630\n" +
                "skim vanilla greek yogurt;99033\n" +
                "skin-on bone-in chicken leg quarters;1005091\n" +
                "skinless boneless chicken breast halves;1045062\n" +
                "skinless boneless chicken thighs;5096\n" +
                "skinned black gram;93718\n" +
                "slaw dressing;43016\n" +
                "slaw mix;10011109\n" +
                "slivered almonds;10012061\n" +
                "smoked paprika;1012028\n" +
                "smoked salmon;15077\n" +
                "smoked sausage;7916\n" +
                "smooth peanut butter;16150\n" +
                "snapper fillets;15101\n" +
                "snow peas;11300\n" +
                "soda water;14121\n" +
                "sour cream;1056\n" +
                "sourdough bowl;99169\n" +
                "sourdough bread;10118029\n" +
                "soy milk;16223\n" +
                "soy protein powder;16122\n" +
                "soy sauce;16124\n" +
                "spaghetti;11420420\n" +
                "spaghetti squash;11492\n" +
                "sparkling wine;43155\n" +
                "spelt flour;93823\n" +
                "spicy brown mustard;1022046\n" +
                "spinach;10011457\n" +
                "sprite;14144\n" +
                "sprouts;11001\n" +
                "squash;10011485\n" +
                "sriracha sauce;1016168\n" +
                "steaks;23232\n" +
                "steel cut oats;93695\n" +
                "stevia;93628\n" +
                "stew meat;10023618\n" +
                "stew vegetables;11583\n" +
                "stock;1006615\n" +
                "store-bought phyllo;18338\n" +
                "stout;93619\n" +
                "strawberries;9316\n" +
                "strawberry jam;10819297\n" +
                "strawberry jello;10219172\n" +
                "stuffing;18082\n" +
                "stuffing mix;18081\n" +
                "sub rolls;98940\n" +
                "sugar;19335\n" +
                "sugar snap peas;10011300\n" +
                "sugar syrup;90480\n" +
                "sukrin sweetener;99190\n" +
                "summer savory;98961\n" +
                "summer squash;11641\n" +
                "sunflower oil;4584\n" +
                "sunflower seeds;12036\n" +
                "sweet chilli sauce;98962\n" +
                "sweet onion;11294\n" +
                "sweet paprika;1002028\n" +
                "sweet pickle juice;93640\n" +
                "sweet pickle relish;11945\n" +
                "sweet potato;11507\n" +
                "sweet tea;14355\n" +
                "sweetened coconut;12109\n" +
                "sweetened condensed milk;1095\n" +
                "sweetened shredded coconut;12179\n" +
                "swiss chard;11147\n" +
                "swiss cheese;1040\n" +
                "taco seasoning mix;2073\n" +
                "taco shells;18360\n" +
                "tahini;12698\n" +
                "tamari;10116124\n" +
                "tapioca flour;93696\n" +
                "tarragon;2041\n" +
                "tart apple;1029003\n" +
                "tea bags;10111111\n" +
                "tequila;10814037\n" +
                "teriyaki sauce;6112\n" +
                "thai basil;1012044\n" +
                "thai chiles;11670\n" +
                "thai red curry paste;93605\n" +
                "thick-cut bacon;10310123\n" +
                "tilapia fillets;15261\n" +
                "toast;18070\n" +
                "toffee bits;19383\n" +
                "tofu;16213\n" +
                "tomatillos;11954\n" +
                "tomato juice;11886\n" +
                "tomato paste;11887\n" +
                "tomato puree;11547\n" +
                "tomato sauce;11549\n" +
                "tomato soup;6159\n" +
                "tomatoes;11529\n" +
                "top blade steak;13523\n" +
                "top round steak;23636\n" +
                "Top Sirloin;23584\n" +
                "tortilla;18364\n" +
                "tortilla chips;19056\n" +
                "triple sec;14534\n" +
                "truffle oil;1024053\n" +
                "tuna;10015121\n" +
                "turbinado sugar;19908\n" +
                "turkey;5165\n" +
                "turkey breast;5696\n" +
                "turkey kielbasa;7955\n" +
                "turmeric;2043\n" +
                "turnips;11564\n" +
                "unbleached flour;10020081\n" +
                "unsalted butter;1145\n" +
                "unsmoked back bacon;10130\n" +
                "unsweetened applesauce;9019\n" +
                "unsweetened coconut milk;12117\n" +
                "unsweetened shredded coconut;10012108\n" +
                "vanilla bean;93622\n" +
                "vanilla bean paste;93813\n" +
                "vanilla essence;1012050\n" +
                "vanilla extract;2050\n" +
                "vanilla frosting;10019230\n" +
                "vanilla instant pudding mix;19206\n" +
                "vanilla protein powder;99076\n" +
                "vanilla wafers;18609\n" +
                "vanilla yogurt;1119\n" +
                "vegan cheese;93701\n" +
                "vegan chocolate chips;98848\n" +
                "vegan margarine;4673\n" +
                "vegetable broth;6615\n" +
                "vegetable oil;4513\n" +
                "vegetarian bacon;16542\n" +
                "vermouth;14132\n" +
                "vinaigrette;4135\n" +
                "vinegar;2053\n" +
                "vodka;14051\n" +
                "walnuts;12155\n" +
                "water;14412\n" +
                "water chestnuts;11590\n" +
                "water-packed tuna;15121\n" +
                "watercress;11591\n" +
                "watermelon chunks;9326\n" +
                "wheat bran;20077\n" +
                "wheat germ;20078\n" +
                "whipped cream;1054\n" +
                "whipped topping;42135\n" +
                "whipping cream;1001053\n" +
                "whiskey;14052\n" +
                "white balsamic vinegar;1012069\n" +
                "white bread;18069\n" +
                "white cake mix;18137\n" +
                "white cheddar;1011009\n" +
                "white chocolate;19087\n" +
                "white chocolate chips;10019087\n" +
                "white onion;10611282\n" +
                "white pepper;2032\n" +
                "white whole wheat flour;93824\n" +
                "white wine;14106\n" +
                "white wine vinegar;1002068\n" +
                "whole allspice berries;1002001\n" +
                "whole chicken;5006\n" +
                "whole coriander seeds;2013\n" +
                "whole cranberry sauce;9081\n" +
                "whole kernel corn;11177\n" +
                "whole star anise;1012002\n" +
                "whole wheat bread;18075\n" +
                "whole wheat flour;20080\n" +
                "whole wheat tortillas;93675\n" +
                "whole-grain mustard;1012046\n" +
                "wine;14084\n" +
                "wine vinegar;2068\n" +
                "winter squash;10111485\n" +
                "won ton wraps;10018368\n" +
                "worcestershire sauce;6971\n" +
                "wraps;10018364\n" +
                "xanthan gum;93626\n" +
                "yeast;18375\n" +
                "yellow bell pepper;11951\n" +
                "yellow cake mix;18144\n" +
                "yellow onion;10511282\n" +
                "yogurt;1116\n" +
                "yukon gold potato;10211362\n" ;
        List<Ingredient> ingredients = null;
        try {
            ingredients = new ArrayList<>();
            String[] ingredientsArr = str.split("\n");
            for (String i : ingredientsArr) {
                Ingredient ingredient = new Ingredient();
                String[] split = i.split(";");
                ingredient.setName(split[0]);
                ingredient.setId(Integer.parseInt(split[1]));
                ingredients.add(ingredient);
            }
        } catch (Exception e) {
        }
        return ingredients;
    }


    public static List<Ingredient> getRandomMainIngredients() {
        String str = "yogurt;1116\n" +
//                "vegetable oil;4513\n" + "tomato sauce;11549\n" + "meat;1015006\n" + "milk;1077\n" + "pork;10010219\n" + "beans;16069\n" +
//                "beef;23572\n" + "avocado;9037\n" + "fish;10115261\n" + "coconut;12104\n" +
                "Annie Pham ;12124\n" + "Bonus;123123";;

        List<Ingredient> sample = null;
        List<Ingredient> ingredients = new ArrayList<>();
        try {
            sample = new ArrayList<>();
            String[] ingredientsArr = str.split("\n");
            for (String i : ingredientsArr) {
                Ingredient ingredient = new Ingredient();
                String[] split = i.split(";");
                ingredient.setName(split[0]);
                ingredient.setId(Integer.parseInt(split[1]));
                sample.add(ingredient);
            }
            while (ingredients.size() < 3) {
                Random rand = new Random();
                Ingredient randomElement = sample.get(rand.nextInt(sample.size()));
                if (!ingredients.contains(randomElement)) {
                    ingredients.add(randomElement);
                }
            }
        } catch (Exception e) {
        }
        return ingredients;
    }
}
