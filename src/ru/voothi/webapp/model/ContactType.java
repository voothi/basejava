package ru.voothi.webapp.model;

public enum ContactType {
    PHONE("Тел."),
    SKYPE("Skype") {
        @Override
        public String toHtml(String value) {
            return "<a href='skype:" + value + "'>" + value + "</a>";
        }
    },
    EMAIL("Почта") {
        @Override
        public String toHtml(String value) {
            return "<a href='mailto:" + value + "'>" + value + "</a>";
        }
    },
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль StackOverflow"),
    HOME_PAGE("Домашняя страница");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String toHtml(String value) {
        return title + ": " + value;
    }
}
