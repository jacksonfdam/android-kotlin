# Proguard Android

## O que é e qual a importância do Proguard

Provavelmente você já deve saber que diferente de sistemas Web convencionais (sites) uma APP Android precisa ter o compilado dela descarregado no device mobile para então esse poder utilizar esse código compilado para a execução do aplicativo.

_Sabendo ou não disso, qual seria o ponto importante no trecho acima?_

O ponto importante é que é possível aplicar engenharia reversa nesse código compilado, consequentemente dando a qualquer outro developer o poder de acesso aos códigos fontes de seu projeto Android.

Com isso será fácil para ele criar um novo projeto (package name) utilizando como base seu algoritmo. Em pouco tempo ter esse novo projeto em produção na Play Store.

O problema fica ainda mais crítico se sua APP Android tiver toda a lógica de negócio somente local, ou seja, rodando somente no device mobile, sem necessidade da lógica de negócio remota, em um backend Web.

Nesse cenário sua APP pode ser clonada rapidamente e em um espaço curto de tempo várias APPs iguais a ela estarem disputando a busca do usuário na PlayStore. Frustrante!

Você já deve ter notado que falei isso, pois o Proguard tem como parte do objetivo dele dificultar o processo de engenharia reversa. Como?

Um dos passos do processamento do Proguard é a ofuscação de código. Trechos como: nomes de classes, métodos, interfaces e atributos. Poderão receber novos e menores rótulos removendo a fácil compreensão do algoritmo do projeto.

Um outro passo importante aplicado pela ferramenta Proguard é o de remoção de código morto. Código que está presente no projeto, mas nunca é utilizado.

Abaixo a imagem de como funciona o processamento Proguard, não somente para projetos Android, mas para qualquer projeto que o utilize:

![](https://www.thiengo.com.br/img/post/normal/vuonf908630oiuvai4acddbe717c8659888184c3978c4f7f2381b44ceb.jpg)

Veja que ainda há outros passos além da ofuscação comentada anteriormente. Por definição em arquivo padrão de configuração do Proguard no Android, o passo "Preverify" nunca é executado, mas você pode ativa-lo, algo que não recomendo, pois ele não é necessário.

Bom, vou ser realista, a imagem acima não é a verdade sobre o processamento Proguard. Lendo a documentação deste você vai perceber que o que realmente acontece é o seguinte:

![](https://www.thiengo.com.br/img/post/normal/vuonf908630oiuvai4acddbe715c11ac9cd68c8635200a63dd414cd5d4.jpg)

Note que logo depois do passo "Optimize" há novamente o passo "Shrink" (remoção de código morto).

_Por que isso?_

Essa repetição do passo "Shrink" é necessária, pois um dos objetivos do passo "Optimize" é colocar constantes e métodos em linha de código.

_O que?_

Isso mesmo. Linha de código. Por exemplo, assuma que em seu projeto tenha o seguinte método:

```java
...
private int soma( int num1, int num2 ){
    int num = num1 + num2;
    return num;
}
...
```

Agora assuma que no método  **onCreate()**  de sua  **MainActivity**  tenha a invocação desse método da seguinte maneira:

```java
...
private static final int NUM_1 = 1;
private static final int NUM_2 = 2;

@Override
protected void onCreate(Bundle savedInstanceState) {
    ...
    Log.i("log", "Soma: "+soma( NUM_1, NUM_2 ));
}
...
```

Obviamente que a resposta seria três, isso quando o código já estivesse sendo executado no device do usuário. Porém, na passo de otimização do Proguard as constantes  **NUM_1**  e  **NUM_2**  seriam, muito provavelmente, colocadas em linha e removidas as declarações delas na classe, como:

```java
...
@Override
protected void onCreate(Bundle savedInstanceState) {
    ...
    Log.i("log", "Soma: "+soma( 1, 2 ));
}
...
```

Esse processo é conhecido também como "Constants inlined".

Um outro provável passo n otimização é a colocação do corpo do método em linha de código, removendo assim a necessidade de manter o método  **soma()** no projeto:

```java
...
@Override
protected void onCreate(Bundle savedInstanceState) {
    ...
    Log.i("log", "Soma: "+( 1 + 2 ));
}
...
```

Esse processo... você já deve ter percebido... é conhecido também como "Method inlined".

Devido a esse processamento de colocar constantes e métodos em linha de código a repetição do passo "Shrink" é necessária, para remover novos códigos mortos.

Lembrando que apesar dessas otimizações deixarem o código menos legível, elas melhoram o desempenho do processamento da aplicação, pois diminui o número de métodos invocados e de variáveis e constantes a serem acessadas em memória para posteriormente terem seus valores lidos.

No Android, utilizando um dos dois arquivos pré-configurados disponíveis a nós developers, o passo "Optimize" está ativo somente no uso do arquivo  **android-proguard-optimize.txt**. No decorrer do artigo iremos falar mais sobre os arquivos padrões de configuração, mas já adiantando, o outro é o  **proguard-android.txt**.

## Executando o ClassyShark para descompilação

Antes de prosseguirmos temos de ter algumas configurações já prontas, mais precisamente uma ferramenta simples e eficiente para a descompilação de um projeto Android (.apk).

Nesse projeto vamos utilizar duas tools. Uma é online e nos permite navegar por todo o projeto, incluindo os códigos internos de classes e métodos. Essa é a  [APK decompiler](http://www.javadecompilers.com/apk "APK decompiler") e está no site Java decompilers.

A outra tool precisaremos executar em nossa máquina. ClassyShark é um .jar que permite o fácil acesso ao conteúdo de um arquivo .apk.

Para baixá-lo acesse  [android-classyshark](https://github.com/google/android-classyshark/releases "android-classyshark") e então clique em ClassyShark.jar.

Para executá-lo precisamos abrir o prompt de comando e então executar:

```shell
java -jar ClassyShark.jar
```

Note que você terá de navegar até o diretório onde se encontra o ClassyShark.jar.

Depois da execução da linha acima no prompt de comando você terá acesso ao uma GUI como a seguinte:

![](https://www.thiengo.com.br/img/post/normal/cdrdvnjiai0ct8lmavnjo54od416518ec3d57b1765c39f19ad5bcf2621.jpg)

Deixe dessa forma. Logo vamos voltar ao ClassyShark para navegarmos no .apk de nosso projeto de exemplo.

## Projeto de exemplo

Nosso projeto de exemplo é exatamente o mesmo projeto que utilizamos no artigo da API de anúncios da In Loco Media. Mais precisamente o artigo: [Estratégia de Anúncios com In Loco Media no Android](https://www.thiengo.com.br/estrategia-de-anuncios-com-in-loco-media-no-android "Estratégia de Anúncios com In Loco Media no Android").

Para acessar o projeto completo entre no GitHub dele em:  [GeoLocationAds](https://github.com/viniciusthiengo/GeoLocationAds "Projeto GitHub GeoLocationAds").

Fique tranquilo que não é preciso entendê-lo, apenas o utilize para acompanhar as explicações aqui do artigo.

Logo depois de descarregar o projeto, abra o Android Studio e execute esse, abrindo-o como projeto Android Studio já existente.

Então vá em "Build" e logo depois clique em "Build APK". Feito isso, navegue no diretório de seu projeto para acessar o  **app-debug.apk**. Em meu sistema ele está em: GeoLocationAds > app > build > outputs > apk:

![](https://www.thiengo.com.br/img/post/normal/2ihee48kq71nnikvss7ndfumq0e82c363739c38a3ea0cc8c4d1599d151.jpg)

Note que ainda não estamos utilizando o Proguard. Se acessar o Gradle APP Level de seu projeto,  **build.gradle (Module: app)**, vai ter a seguinte configuração:

```yml
apply plugin: 'com.android.application'

android {
    compileSdkVersion 25
    buildToolsVersion "24.0.3"
    defaultConfig {
        applicationId "br.com.thiengo.geolocationads"
        minSdkVersion 10
        targetSdkVersion 25
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
        exclude group: 'com.android.support', module: 'support-annotations'
    })
    compile 'com.android.support:appcompat-v7:25.0.0'
    compile 'com.android.support:design:25.0.0'
    testCompile 'junit:junit:4.12'

    /* Required */
    compile 'com.inlocomedia.android:android-sdk:2.3.+'

    /* Required: Access GooglePlayServices advertising id */
    compile 'com.google.android.gms:play-services-ads:9.8.0'

    /* Required for Notification Ads. */
    /* Recommended for additional Ad targeting */
    compile 'com.google.android.gms:play-services-location:9.8.0'
}
```

Note que em  **buildTypes**  somente tem a configuração de **release**  e mesmo assim o Proguard está desativado, pois  **minifyEnabled**  está com o valor  **false**.

De qualquer forma, em ambiente de desenvolvimento o **minifyEnabled** igual a  **true**  do build de  **release**  não funcionaria, pois estaríamos criando a versão de  **debug**  nesse ambiente.

Vamos dar uma pausa por aqui quanto a configuração do Gradle de nosso projeto e vamos voltar ao  **app-debug.apk**. Agora que sabe onde encontrá-lo, o .apk, acesse o GUI do ClassyShark e então clique no ícone de diretório no topo esquerdo da GUI e navegue até o  **app-debug.apk**.

Abra ele clicando em "Open". Agora expanda "classes" e logo depois "classes.dex". Vá até o package de seu projeto e expanda-o. Clique em  **MainActivity**  e ao lado verá trechos do código, como na imagem abaixo:

![](https://www.thiengo.com.br/img/post/normal/cdrdvnjiai0ct8lmavnjo54od47461bc08ffb37e4130bada1954c86a04.jpg)

Veja que o código está exatamente como você (eu) desenvolveu. Os mesmo nomes de variáveis de instância, classes e métodos.

Vários outros arquivos e diretórios de libraries que são referenciadas no gradle do projeto estão presentes também. Até mesmo os recursos, expandindo "res" ao invés de "classes" você terá acesso a eles.

Note que mesmo que o compilador dos arquivos .java do projeto tente aplicar alguma otimização, o objetivo final dele não é esse e muito sobre a otimização é deixado para outras ferramentas que podem ser utilizadas em projetos Java.

Um outro fator a se notar é que o .apk está de graça para qualquer outro desenvolvedor, digo, assim que o developer colocar a APP no device dele ele terá acesso ao .apk. Logo, poderá realizar o mesmo processo de descompilação e, por que não, criar uma APP muito similar a sua.

Provavelmente você deve estar se perguntando:  _por que você utilizou o "Build APK" ao invés de utilizar o simples "Run app" ou "Debug app"?_

Não os utilizei, pois esses ativam o "Instant Run" que por padrão desativa o Proguard, mesmo quando você definiu as configurações para o buildType debug.

Ok, com o .apk sem ter passado pela ferramenta Proguard e explicado o porquê do não uso do "Run app" e "Debug app" podemos ativar o Proguard e assim gerar outro  **app-android.apk**.

No Gradle APP Level coloque o seguinte trecho em  **buildTypes**:

```yml
...
buildTypes {
    debug {
        minifyEnabled true
        proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
    }
    release {
        minifyEnabled false
        proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
    }
}
...
```

Esse é o build de debug definido. Sincronize o projeto. Agora novamente acesse "Build", mas agora clique em "Clean Project" para que não tenhamos problemas com .apk em cache. Finalizado o processo de clean, acesse "Build" e clique em "Build APK".

Agora com o ClassyShark navegue até o novo  **app-debug.apk**  e abra ele. Novamente navegue até a  **MainActivity**  e então terá algo similar a figura abaixo:

![](https://www.thiengo.com.br/img/post/normal/cdrdvnjiai0ct8lmavnjo54od4057a64caf37e75d361404e05c8cef267.jpg)

Note que agora alguns membros da  **MainActivity**  tiveram os nomes alterados. Alguns diretórios e classes não mais estão presentes no projeto. Isso devido, principalmente, aos passos "Shrink" e "Obfuscate" do Proguard.

O primeiro passo remove qualquer código de classe, atributo ou método morto, que não é referenciado e utilizado no projeto. O segundo renomeia o que for possível para aumentar ainda mais a performance de uso de memória pela APP (rótulos menores) além de dificultar em muito o processo de engenharia reversa, removendo a fácil compreensão do projeto.

Um ponto que você provavelmente notou é a diminuição em bytes do tamanho do **app-debug.apk**. Se estiver utilizando o mesmo projeto do exemplo você deve ter obtido nessa versão final, com o Proguard sendo utilizado, um .apk com aproximados 4 MB ante ao anterior, sem processamento do Proguard, de 5.2 MB.

Nos exemplos da documentação do Proguard há projetos com ganhos acima de 60% do tamanho real.

Note também que alguns novos arquivos foram adicionados ao projeto. No diretório "outputs", o mesmo onde se encontra o diretório "apk", expanda "mapping" e "debug". Haverá quatro novos arquivos:

-   **dump.txt**: contém toda a estrutura interna de classes que está no arquivo .apk;
-   **mapping.txt**: contém todas as classes e membros de classes que tiveram os nomes ofuscados e seus respectivos novos nomes ofuscados. Esse arquivo é o mais importante, mais a frente no artigo comentaremos mais sobre ele;
-   **seeds.txt**: contém todas as classes e membros de classes que não foram ofuscados;
-   **usage.txt**: contém todas as classes e membros dessas que foram removidos do .apk final.

Acima, entenda classe como também podendo ser Interface.

Todos os arquivos citados acima têm sua importância depois do processamento do projeto com a ferramenta Proguard.

Porém, assim que enviar sua APP, .apk, a Play Store, esta estará com o código otimizado e ofuscado, logo, os possíveis problemas que aparecerem em devices de usuários terão uma pilha de erro, stack trace, ofuscada, não legível.

O arquivo  **mapping.txt**  permite a desofuscação da pilha de erros trazendo os nomes reais das entidades de seu projeto a ela.

A melhor maneira de fazer isso é enviar o  **mapping.txt**  para a versão dele de sua APP no dashboard da Play Store. Caso já tenha conta na Play Store e esteja enviando sua nova APK, otimizada pelo Proguard.

Logo depois de fornecer a APK acesse a área de sua APP no dashboard, então clique em "Falhas e ANRs". Agora clique em "Arquivos de desofuscação".

Na versão atual de sua APP, digo, a versão referente ao  **mapping.txt**, clique em "Enviar" e selecione o  **mapping.txt**  do .apk dessa versão:

![](https://www.thiengo.com.br/img/post/normal/cdrdvnjiai0ct8lmavnjo54od4679b38d026c13bd9b545086aa5f6762d.jpg)

Dessa forma as stack traces enviadas dessa versão que você forneceu o  **mapping.txt**  serão já desofuscadas. Fazendo isso você também não tem a necessidade de criar um diretório extra somente para guardar os mapping.txt de cada nova .apk gerada, pois cada uma nova criará novos arquivos e os anteriores serão sobrescritos.

Com isso você ainda deve estar se perguntando:  _e os arquivos  **proguard-android.txt**  e  **proguard-rules.pro**? Para que esses servem?_

O  **proguard-android.txt**  é o arquivo de configurações padrões a serem utilizadas pelo Proguard em um projeto Android.

_Configurações padrões?_

Sim. O Proguard trabalha baseado em um arquivo de configurações onde todos os caminhos e limites de processamento que devem ser adotados são descritos.

No diretório de seu Android SDK você encontrará dois arquivos de configuração Proguard que podem ser utilizados como arquivos padrões em um projeto Android.

Vá ao buscador de seu sistema operacional e digite: "proguard-android-optimize.txt".

Logo depois acesse o diretório desse arquivo. Abra o **proguard-android.txt**  e então abra o **proguard-android-optimize.txt**.

Veja que ambos têm uma série de configurações, somente o início de configuração de **proguard-android-optimize.txt**  é diferente, pois ele ativa o passo de otimização com a opção **-optimizationpasses 5**  definida.

Na documentação do Android não há nada explícito sobre manter o uso de alguma dessas duas configurações padrões fornecidas pelo Android SDK. Porém na documentação do Proguard há vários informes para não alterarmos elas e nem mesmo deixar de utiliza-las caso o uso do Proguard seja feito.

Com isso terminamos com os arquivos padrões, pois somente queria lhe deixar ciente deles. A recomendação é que utilize primeiro a versão sem o passo de "Optimize", ou seja, o  **proguard-android.txt**.

Logo depois, caso esteja seguro e tenha testado sua APP otimizada e ofuscada, utilize a versão com o passo "Optimize" ativo,  **proguard-android-optimize.txt**.

O arquivo  **proguard-rules.pro**  é o arquivo em que devemos fornecer as configurações extras que serão anexadas as configurações padrões.

Note que algumas libraries têm certas recomendações e configurações quanto ao uso do Proguard.

Um exemplo é a API de anúncios do  [AppOdeal](https://www.thiengo.com.br/monetizacao-eficiente-no-android-com-appodeal "Monetização Eficiente no Android com APPODEAL"). Caso esteja com o proguard ativo você deve colocar o trecho de código do link a seguir em seu **proguard-rules.pro**  para que seu projeto funcione sem problemas. Segue link:  [AppOdeal Troubleshooting - Message dontskipnonpubliclibraryclassmembers](https://www.appodeal.com/sdk/troubleshooting?framework=1&full=1 "AppOdeal Troubleshooting - Message dontskipnonpubliclibraryclassmembers").

Para saber quais as opções possíveis e as respectivas funcionalidades delas, acesse a documentação do Proguard em: [Proguard Doc](http://proguard.sourceforge.net/ "Proguard Doc").

Para um exemplo de uso real de alguma das opções do Proguard, acesse o arquivo  **proguard-rules.pro**  direto do Android Studio. Ele está no mesmo nível que os arquivos  **build.gradle**:

![](https://www.thiengo.com.br/img/post/normal/cdrdvnjiai0ct8lmavnjo54od43ac22f99660ec5d4ad1b1d220a413da3.jpg)

Agora adicione o seguinte código a ele:

```yml
-keep class br.com.thiengo.geolocationads.domain.GamesAdapter {
   <fields>;
}
```

Note que sempre que for referenciar alguma classe ou Interface é preciso colocar o path e nome completos.

Antes de dar o clean e logo depois o "Build APK", volte ao ClassyShark e navegue até a classe  **GamesAdapter**  ofuscada. Provavelmente o nome dela será  **b**, mas com certeza será a única classe que herdará de  **BaseAdapter**, caso esteja com o mesmo projeto do exemplo. Você terá algo similar a:

![](https://www.thiengo.com.br/img/post/normal/cdrdvnjiai0ct8lmavnjo54od472e6490406772a3fe265256997cc99ed.jpg)

Note que as variáveis de instância, nome da classe e alguns métodos foram ofuscados.

Com o código  **-keep**  que colocamos no  **proguard-rules.pro**  nós vamos manter o nome da classe e das variáveis de instância intocáveis, além de removermos qualquer chance de essas entidades serem retiradas do projeto no passo "Shrink", mesmo se essas não estivessem sendo referenciadas em nenhum ponto do projeto.

Agora vá em "Build", logo depois em "Clean Project". Com o processo de clean finalizado, volte a "Build" e logo depois clique em "Build APK".

Uma nova  **app-debug.apk**  será gerada. Agora com o ClassyShark navegue até esse novo .apk. Logo depois expanda classes, classes.dex e então acesse o package de seu projeto e em seguida  **GamesAdapter**.

Note que o nome da classe e das variáveis de instância não foram ofuscados:

![](https://www.thiengo.com.br/img/post/normal/cdrdvnjiai0ct8lmavnjo54od48ce8c2eff3497403e34c803baae010b5.jpg)

Alguns métodos ainda foram ofuscados, somente os que não foi possível ofuscar (sem relação com nossa definição em  **proguard-rules.pro**) é que o nome foi mantido.

Uma outra maneira de mantermos os nomes e as entidades é utilizando a anotação  **@Keep**  na classe ou em algum membro da classe, atributo ou método. Exemplo:

```java
public class GamesAdapter extends BaseAdapter {
    @Keep
    private LayoutInflater layoutInflater;
    @Keep
    private List<Game> list;

    ...
}
```

O código acima tem o exato mesmo efeito que o código utilizando a opção  **-keep**  em  **proguard-rules.pro**, porém a versão acima dispensa o código nesse arquivo .pro.

O  **@Keep**  em qualquer um dos membros de uma classe implica também em manter o nome da classe intacto. No caso colocamos  **@keep**  nas duas variáveis de instância para que ambas sejam mantidas, caso contrário somente a que tivesse o  **@keep**  seria certeza de ser mantida.

Agora acesse o  **proguard-rules.pro**  e remova o código  **-keep**  que adicionamos a ele, pois vamos gerar uma  **Exception**  no projeto e então aplicar a desofuscação de pilha de erros via prompt de comando com o  **mapping.txt**.

Antes de provocarmos uma  **Exception**, acesse o Gradle APP Level e então insira a seguinte flag logo acima de  **minifyEnabled**  do  **buildType**  **debug**:

```yml
...
buildTypes {
    debug {
        shrinkResources true
        minifyEnabled true
        proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
    }
    ...
}
...
```

Sincronize o projeto. Vá em "Build" e logo depois clique em "Clean Project". Então volte a "Build" e clique em "Build APK". Acesse o  **app-debug.apk** e notará que agora ele tem aproximadamente 3.9 MB, ainda menor que a versão com somente o Proguard processando o .apk original.

_Somente o Proguard processando?_

Sim. O Proguard não remove recursos do projeto. O máximo que ele consegue fazer é ofuscar o nome dos recursos e o nome de classes dentro dos recursos (um .txt, por exemplo).

A opção  **shrinkResources true** ativa uma ferramenta que está disponível no Android Plugin que está no Gradle. Essa ferramenta é responsável por remover recursos que não estão sendo utilizados na APP. Recursos como: imagens, XMLs, vídeos, áudios, entre outros.

Com isso temos um projeto ainda mais otimizado. Porém siga as recomendações da documentação do Android. Somente ative o **shrinkResources** depois de já ter rodado a versão somente com o Proguard ativo.

Acesse a página  [Shrink Your Code and Resources](https://developer.android.com/studio/build/shrink-code.html#shrink-resources "Shrink Your Code and Resources") da documentação do Android para saber um pouco mais sobre as opções de **shrinkResources**, mas resumidamente o  _core_  dela é o que foi mostrado aqui.

Agora sim podemos simular nossa  **Exception**. Acesse a classe  **GamesAdapter**, mais precisamente o método  **getView()**, e coloque o seguinte trecho de código:

```java
public class GamesAdapter extends BaseAdapter {
    ...

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ...

        int aux = holder == null ? 1 : 0;
        int aux2 = 1 / aux;
        Log.i("log", "Bug: "+aux2);

        return convertView;
    }
    ...
}
```

Dessa vez teremos de utilizar um emulador, logo, execute seu AVD, aqui vamos utilizar um com Android API 23. Logo depois vá em "Build" e então em "Clean Project". Volte em "Build", depois do clean, e clique em "Build APK".

Navegue até o arquivo  **app-debug.apk**. Clique e arraste ele para dentro do emulador. Aguarde a instalação e logo depois abra a APP. Terá algo como:

![](https://www.thiengo.com.br/img/post/normal/cdrdvnjiai0ct8lmavnjo54od4bd38d38a3f343049af413a589ab80d06.jpg)

E uma pilha de erros com o início similar a:

![](https://www.thiengo.com.br/img/post/normal/cdrdvnjiai0ct8lmavnjo54od4edbb10620da820861673ec602fda6f77.jpg)

Note a classe  **b**, que é a classe  **GamesAdapter**  ofuscada.

Copie toda a stack trace e cole dentro de um arquivo .txt. Aqui vou utilizar o arquivo de nome  **stack-trace.txt**. Logo depois encontre o arquivo  **retrace.bat** (caso esteja no Windows) ou  **retrace.sh**  (caso MAC OS ou Linux).

E então, com o prompt de comando aberto, coloque o path e arquivo  **retrace**, logo depois o path e arquivo  **mapping.txt**  e em seguida o path e arquivo  **stack-trace.txt**. Assim, execute:

![](https://www.thiengo.com.br/img/post/normal/cdrdvnjiai0ct8lmavnjo54od4c9db0fb7bd6d874f4fdc7954684e58d9.jpg)

Essa é uma maneira de desofuscar uma stack trace e entender onde está o problema em seu projeto. Porém essa maneira não remove a importância de enviar o arquivo  **mapping.txt**  para a versão correta de sua APP na Play Store.

Agora para finalizar nosso exemplo, acesse a  **GameActivity**  e acrescente o seguinte código:

```java
public class GameActivity extends AppCompatActivity {
    ...
    private static final int NUM_1 = 1;
    private static final int NUM_2 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ...
        Log.i("log", "Soma: "+soma( NUM_1, NUM_2 ));
    }


    private int soma( int num1, int num2 ){
        int num = num1 + num2;
        return num;
    }
    ...
}
```

Remova o código problemático que adicionamos em  **GamesAdapter**  e logo depois limpe o projeto indo em "Clean Project" e então crie uma nova .apk indo em "Build APK".

Agora acesse o site  [Java Decompilers](http://www.javadecompilers.com/apk "Java Decompilers"), então clique em "APK decompiler" e logo em seguida em "Choose File". Navegue até seu novo  **app-debug.apk**  e selecione-o. Agora clique em "Upload and Decompile". Aguarde alguns segundos.

Em seguida clique em "Save":

![](https://www.thiengo.com.br/img/post/normal/cdrdvnjiai0ct8lmavnjo54od47a70e851c8d5229ba8e2881c2c993560.jpg)

Logo depois descompacte o arquivo baixado. Abra o arquivo e navegue para: br > com > thiengo > geolocationads. Então abra em seu IDE, ou qualquer software de códigos, o arquivo  **GameActivity.java**:

```java
public class GameActivity extends C0609f {
    ...

    protected void onCreate(Bundle bundle) {
        ...
        Log.i("log", "Soma: " + m5110a(1, 2));
    }

    private int m5110a(int i, int i2) {
        return i + i2;
    }
    ...
}
```

Note que além dos nomes ofuscados, o código de declaração das constantes foi removido. Recordando o início do artigo: o nome desse processo é constants inlined, onde os valores são colocados nos lugares das referências as constantes.

Isso também ocorre com métodos, mas na documentação do Proguard está claro que não é certo que esse tipo de otimização vai acontecer, pode ocorrer.

Como desafio, altere o Gradle APP Level para processar o Proguard com o arquivo proguard-android-optimize.txt e veja o resultado de seu .apk.

## Fontes

[Documentação Proguard](http://proguard.sourceforge.net/ "Documentação Proguard")

[Shrink Your Code and Resources](https://developer.android.com/studio/build/shrink-code.html#shrink-resources "Shrink Your Code and Resources")

[What is preverification?](http://www.allinterview.com/showanswers/9007/what-is-preverification.html "What is preverification?")
[Proguard Android](https://www.thiengo.com.br/proguard-android)