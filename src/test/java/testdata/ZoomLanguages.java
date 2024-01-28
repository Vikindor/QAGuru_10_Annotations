package testdata;

public enum ZoomLanguages {
    English("AI that makes you more"),
//    Español("AI que lo hace más"),
//    Deutsch("KI-gestützte Funktionen, mit denen Sie"),
//    简体中文("一个用于"), //zh-cn
//    繁體中文("一個平台，"), //zh-tw
//    Français("Une IA au service de l’efficacité"),
//    Português("IA que aumenta a sua"),
//    日本語("皆様の"), //ja
    Русский("Одна платформа для");
    public final String description;

    ZoomLanguages(String description) {
        this.description = description;
    }

}