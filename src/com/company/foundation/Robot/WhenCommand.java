package com.company.foundation.Robot;

public class WhenCommand extends CommandBase {

    public interface WhenFunction {
        boolean when();
    }

    private final WhenFunction when;
    private final CommandBase command;

    public WhenCommand(WhenFunction when, CommandBase command) {
        this.when = when;
        this.command = command;
    }

    @Override
    public void initialize() {
        command.doinitialize();
    }

    @Override
    public void execute() {
        if (when.when()) command.doExecute();
    }

    @Override
    public void end() {
        command.doEnd();
    }

    @Override
    public boolean isFinished() {
       return command.getIsFinished();
    }
}
