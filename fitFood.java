package com.example.fitpeak;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class fitFood extends AppCompatActivity
{
    String[] cars = {"Asian-inspired Noodle Salad Recipe with optional Chicken:\n" +
            "Salad\n" +
            "\n" +
            "1 1/2 cups rice noodles, cooked and cut\n" +
            "3 cups chopped bok choy (or lettuce of choice)\n" +
            "2 cups shredded (red) cabbage\n" +
            "3/4 cup matchstick carrots (or grated)\n" +
            "2 green onions, chopped and primarily green tops\n" +
            "4 mini (Persian) cucumbers, chopped (or 1 large cucumber)\n" +
            "1 large red bell pepper (chopped)\n" +
            "1 1/2 cups shelled edamame\n" +
            "Dressing\n" +
            "\n" +
            "3 tablespoons tahini\n" +
            "1 tablespoon toasted sesame oil\n" +
            "3 tablespoons low sodium tamari (or soy sauce)\n" +
            "1 tablespoon maple syrup\n" +
            "1 tablespoon rice (or white) vinegar\n" +
            "juice from 1/2 lime (or more to taste)\n" +
            "2 oz water (or more/less for desired consistency)\n" +
            "Optional protein boost\n" +
            "\n" +
            "1lb chicken breast tenders, or breasts cut into 1-inch pieces\n" +
            "2 teaspoons ginger powder\n" +
            "1 teaspoon ground cumin\n" +
            "1 teaspoon sea salt\n" +
            "2 teaspoons cracked pepper"+
            "2 teaspoons ginger powder\n" +
            "step 1:\n" +
            "Add all ingredients for the salad to a bowl.\n" +
            "step 2\n"+
            "Whisk the ingredients for the sesame dressing and season to taste with tamari and lime/vinegar and adjust consistency by adding tablespoons of water.\n"+
            "step 3\n"+
            "Bring everything together by drizzling the dressing on top of the salad and gently toss to coat.  If you’re doing this for meal prep, then keep the dressing separate so not to wilt the veggies.\n" +
            "\n" +
            "If you desire to boost the protein, season the chicken breast (or protein of choice) and cook in a nonstick skillet!"
            ,"Easy Pea & Spinach Carbonara\n"+ "Ingredients\n" +
            "1 ½ tablespoons extra-virgin olive oil\n" +
            "\n" +
            "½ cup panko breadcrumbs, preferably whole-wheat\n" +
            "\n" +
            "1 small clove garlic, minced\n" +
            "\n" +
            "8 tablespoons grated Parmesan cheese, divided\n" +
            "\n" +
            "3 tablespoons finely chopped fresh parsley\n" +
            "\n" +
            "3 large egg yolks\n" +
            "\n" +
            "1 large egg\n" +
            "\n" +
            "½ teaspoon ground pepper\n" +
            "\n" +
            "¼ teaspoon salt\n" +
            "\n" +
            "1 (9 ounce) package fresh tagliatelle or linguine\n" +
            "\n" +
            "8 cups baby spinach\n" +
            "\n" +
            "1 cup peas (fresh or frozen)\n"+
            "Put 10 cups of water in a large pot and bring to a boil over high heat.\n" +
            "\n step 1:\n" +
            "Meanwhile, heat oil in a large skillet over medium-high heat. Add breadcrumbs and garlic; cook, stirring frequently, until toasted, about 2 minutes. Transfer to a small bowl and stir in 2 tablespoons Parmesan and parsley. Set aside.\n" +
            "\nstep 2: \n" +
            "Whisk the remaining 6 tablespoons Parmesan, egg yolks, egg, pepper and salt in a medium bowl.\n" +
            "\nstep 3:\n" +
            "Cook pasta in the boiling water, stirring occasionally, for 1 minute. Add spinach and peas and cook until the pasta is tender, about 1 minute more. Reserve 1/4 cup of the cooking water. Drain and place in a large bowl.\n" +
            "\nstep 4: \n" +
            "Slowly whisk the reserved cooking water into the egg mixture. Gradually add the mixture to the pasta, tossing with tongs to combine. Serve topped with the reserved breadcrumb mixture."
            + "\n\n\n\n\n\n\n\n\n",

            "Chhole (Chickpea Curry)\n"+
                    "Ingredients\n" +
                    "1 medium serrano pepper, cut into thirds\n" +
                    "\n" +
                    "4 large cloves garlic\n" +
                    "\n" +
                    "1 2-inch piece fresh ginger, peeled and coarsely chopped\n" +
                    "\n" +
                    "1 medium yellow onion, chopped (1-inch)\n" +
                    "\n" +
                    "6 tablespoons canola oil or grapeseed oil\n" +
                    "\n" +
                    "2 teaspoons ground coriander\n" +
                    "\n" +
                    "2 teaspoons ground cumin\n" +
                    "\n" +
                    "½ teaspoon ground turmeric\n" +
                    "\n" +
                    "2 ¼ cups no-salt-added canned diced tomatoes with their juice (from a 28-ounce can)\n" +
                    "\n" +
                    "¾ teaspoon  kosher salt\n" +
                    "\n" +
                    "2 15-ounce cans chickpeas, rinsed\n" +
                    "\n" +
                    "2 teaspoons garam masala\n" +
                    "\n" +
                    "Fresh cilantro for garnish\nstep 1: \n"+
                    "Pulse serrano, garlic and ginger in a food processor until minced. Scrape down the sides and pulse again. Add onion; pulse until finely chopped, but not watery.\n" +
                    "\nstep 2: \n" +
                    "Heat oil in a large saucepan over medium-high heat. Add the onion mixture and cook, stirring occasionally, until softened, 3 to 5 minutes. Add coriander, cumin and turmeric and cook, stirring, for 2 minutes.\n" +
                    "\nstep 3: \n" +
                    "Pulse tomatoes in the food processor until finely chopped. Add to the pan along with salt. Reduce heat to maintain a simmer and cook, stirring occasionally, for 4 minutes. Add chickpeas and garam masala, reduce heat to a gentle simmer, cover and cook, stirring occasionally, for 5 minutes more. Serve topped with cilantro, if desired."
                    + "\n\n\n\n\n\n\n\n\n",

            "Classic Sesame Noodles with Chicken"+
                    "Ingredients\n" +
                    "8 ounces whole-wheat spaghetti\n" +
                    "\n" +
                    "3 tablespoons toasted (dark) sesame oil\n" +
                    "\n" +
                    "2 scallions, chopped\n" +
                    "\n" +
                    "1 tablespoon minced garlic\n" +
                    "\n" +
                    "2 teaspoons minced fresh ginger\n" +
                    "\n" +
                    "1 teaspoon brown sugar\n" +
                    "\n" +
                    "2 tablespoons reduced-sodium soy sauce\n" +
                    "\n" +
                    "2 tablespoons ketchup\n" +
                    "\n" +
                    "8 ounces cooked boneless, skinless chicken breast, shredded\n" +
                    "\n" +
                    "1 cup julienned carrots\n" +
                    "\n" +
                    "1 cup sliced snap peas\n" +
                    "\n" +
                    "3 tablespoons toasted sesame seeds\nstep 1: \n"+
                    "Cook spaghetti in a pot of boiling water according to package directions. Drain, rinse and transfer to a large bowl.\n" +
                    "\nstep 2\n" +
                    "Combine sesame oil, scallions, garlic, ginger and brown sugar in a small saucepan. Heat over medium heat until starting to sizzle. Cook for 15 seconds. Remove from heat and stir in soy sauce and ketchup. Add to the noodles along with chicken, carrots, snap peas and sesame seeds; gently toss to combine."
                    + "\n\n\n\n\n\n\n\n\n"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        Random random = new Random();
        TextView rcp = findViewById(R.id.rcpp);
        Button button1 = (Button) findViewById(R.id.but);
        button1.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        int r = random.nextInt(3);
                        rcp.setText(cars[r]);
                        TextView textView = findViewById(R.id.textView);
                        textView.setText("");
                        rcp.setMovementMethod(new ScrollingMovementMethod());
                    }
                }
        );

    }

}
