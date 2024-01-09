package com.company.foundation.Robot;

public abstract class CommandBase {

    private boolean isInitialize = false;
    private boolean isDone = false;
    private boolean hasEnded;

    public void doinitialize() {
        if (!isInitialize) {
            isInitialize = true;
            initialize();
        }
    }

    public void doExecute() {
        if (!isDone) {
            execute();
        }
    }

    public void doEnd() {
        if (!hasEnded) {
            hasEnded = true;
            end();
        }
    }

    public boolean getIsFinished() {
        isDone = isFinished();
        return isDone;
    }

    public abstract void initialize();

    public abstract void execute();

    public abstract void end();

    public abstract boolean isFinished();
}
