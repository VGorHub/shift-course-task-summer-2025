package ru.gigastack.cli;

public class HelpRequested extends RuntimeException {
    public HelpRequested() {
        super("HELP_REQUESTED");
    }
}
