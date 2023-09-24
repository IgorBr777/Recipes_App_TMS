# Recipes_App_TMS
The Recipes mobile application, with which you can find recipes with photos, a list of ingredients and a description of the cooking process. In the app, you can add your favorite recipes to favorites, delete them, and switch the app to a dark theme.
# Preview
![recipes](https://github.com/IgorBr777/Recipes_App_TMS/assets/114432428/b3632c80-59fc-4a80-bdf1-6d13b9bd693c)   ![search_recipe](https://github.com/IgorBr777/Recipes_App_TMS/assets/114432428/ee495149-0aae-410b-b754-25b9f48c89c8)





![recipe](https://github.com/IgorBr777/Recipes_App_TMS/assets/114432428/2dda5e52-b6ef-4322-a9cd-349116d0e89d)    ![favor_recipes](https://github.com/IgorBr777/Recipes_App_TMS/assets/114432428/4edbcab0-a51f-4b04-8aa0-7d56cdc5071e)






![dark_theme_1](https://github.com/IgorBr777/Recipes_App_TMS/assets/114432428/4484555c-1eac-44c6-9542-b74e147969c6)


# Architecture
Recipes app follows the official architecture guidance.
![architecture layers](https://github.com/IgorBr777/Recipes_App_TMS/assets/114432428/9283d40f-f6ea-434a-a3a9-3a3ece9c245c)
# Tech Stacks
Kotlin - Programming language used.

MVVM - The development pattern used.

Android Architecture Components - A collections of libraries that help you design rebust, testable and maintainable apps.

-Room - Local persistence database.
 
-LiveData - Observable data holder that notify views when underlying data changes.

-Navigation component - Fragment routing handler.

-Hilt - Dependency injection.

Coroutine - Concurrency design pattern for asynchronous programming.

Flow - Stream of value that returns from suspend function.

Retrofit2 + Gson - RESTful API and networking client.

Picasso - Image loading.

SharedPreferences - Permanent storage on the Android platform used by applications to store their settings.
