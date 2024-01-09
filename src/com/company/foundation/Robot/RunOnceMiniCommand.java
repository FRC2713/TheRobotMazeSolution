package com.company.foundation.Robot;

public class RunOnceMiniCommand extends CommandBase {

    public interface RunFunction {
        void run();
    }

    private RunFunction command;

    public RunOnceMiniCommand(RunFunction run) {
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
        return true;
    }
}
