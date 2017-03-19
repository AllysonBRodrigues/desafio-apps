# Desafio INFOGLOBO

Desafio passado pela INFOGLOBO para criação de um aplicativo consumindo a API e exibição de notícias para o usuário.

## Ferramenta de desenvolvimento
- Android Studio 2.3

## Requisitos
- JDK 1.8
- Android SDK.
- Android N (API 25) .
- Ultimas versões do Android SDK Tools e build tools.

## Bibliotecas
- Support libraries
- RecyclerViews
- Constraint Layout
- Retrofit 2
- Butterknife
- Mockito
- Gson
- Picasso
- Joda-Time
- TextJustify-Android 

## Dependências
   - def version_campact = "25.3.0"
    - compile fileTree(dir: 'libs', include: ['*.jar'])
    - androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
        exclude group: 'com.android.support', module: 'support-annotations'
    })
    - testCompile "org.powermock:powermock-api-mockito2:1.6.+"
    - testCompile "org.powermock:powermock-module-junit4:1.6.+"
    - testCompile "org.mockito:mockito-core:1.10.19"
    - compile "com.android.support:appcompat-v7:$version_campact"
    - compile "com.android.support:design:$version_campact"
    - compile "com.android.support:support-v4:$version_campact"
    - compile "com.android.support:cardview-v7:$version_campact"
    - compile "com.android.support:recyclerview-v7:$version_campact"
    - compile "com.android.support:appcompat-v7:$version_campact"
    - compile "com.android.support:support-v4:$version_campact"
    - compile 'com.android.support.constraint:constraint-layout:1.0.2'
    - compile 'com.squareup.retrofit2:retrofit:2.2.0'
    - compile 'com.squareup.retrofit2:converter-gson:2.2.0'
    - compile 'com.google.code.gson:gson:2.8.0'
    - compile 'com.squareup.picasso:picasso:2.5.2'
    - compile 'com.jakewharton:butterknife:8.5.1'
    - compile 'com.github.bluejamesbond:textjustify-android:2.1.6'
    - compile 'com.android.support:support-v4:25.3.0'
    - compile 'joda-time:joda-time:2.9.7'
    - testCompile 'junit:junit:4.12'
    - annotationProcessor 'com.jakewharton:butterknife-compiler:8.5.1'

## Telas

- Tela principal onde é exibida a reportagem de capa, uma lista com outras reportagens e  
o menu lateral contendo a lista de editoriais

[![CAPA](https://github.com/AllysonBRodrigues/desafio-apps/blob/master/screenshots/capa.jpg)](https://github.com/AllysonBRodrigues/desafio-apps/blob/master/screenshots/capa.jpg)

- Menu onde se tem acesso aos editoriais do 'O GLOBO'

[![MENU](https://github.com/AllysonBRodrigues/desafio-apps/blob/master/screenshots/menu.jpg)](https://github.com/AllysonBRodrigues/desafio-apps/blob/master/screenshots/menu.jpg)

- Reportagem selecionada pelo usuário com a possibilidade de retornar a reportagem ao topo clicando no menu na lateral superior direita da tela

[![REPORTAGEM](https://github.com/AllysonBRodrigues/desafio-apps/blob/master/screenshots/reportagem.jpg)](https://github.com/AllysonBRodrigues/desafio-apps/blob/master/screenshots/menu.jpg)

- Link Externo dos editoriais carregados dentro de um webview com a possibilidade de retornar a reportagem ao topo clicando no menu na lateral superior direita da tela

[![REPORTAGEM](https://github.com/AllysonBRodrigues/desafio-apps/blob/master/screenshots/link_externo.jpg)](https://github.com/AllysonBRodrigues/desafio-apps/blob/master/screenshots/link_externo.jpg)

# Sobre mim
Programador mobile, apaixonado pela profissão e a 5 anos focado no mercado de desenvolvimento móvel. Autodidata e com fome de conhecimento sempre procurando me aperfeiçoar buscando crescimento profissional e pessoal.

