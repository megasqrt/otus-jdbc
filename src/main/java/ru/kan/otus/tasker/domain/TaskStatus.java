package ru.kan.otus.tasker.domain;


public enum TaskStatus {
    NEW(1, "NEW"), INWORK(2, "INWORK"), DONE(3, "DONE");

    private final Integer key;
    private final String value;

    TaskStatus(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
/*

    static {
        NEW.previous = List.of();
        NEW.next = List.of(TaskStatus.INWORK, TaskStatus.DONE);

        INWORK.previous = List.of(TaskStatus.NEW);
        INWORK.next = List.of(TaskStatus.DONE);

        DONE.previous = List.of(TaskStatus.NEW, TaskStatus.INWORK);
        DONE.next = List.of();
    }

    @Getter
    private List<TaskStatus> previous;

    @Getter
    private List<TaskStatus> next;
*/


/*
    @Getter
    private String name;*/


}