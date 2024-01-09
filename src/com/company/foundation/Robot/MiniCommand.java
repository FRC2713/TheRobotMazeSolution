package com.company.foundation.Robot;

public class MiniCommand extends CommandBase {

    public interface RunFunction {
        void run();
    }

    private RunFunction command;

    public MiniCommand(RunFunction run) {
        command = run;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        command.run();
    }

    @Override
    public void end() {
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
