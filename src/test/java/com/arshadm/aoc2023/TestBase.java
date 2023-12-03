package com.arshadm.aoc2023;

import org.mockito.internal.util.io.IOUtil;

import java.util.Collection;
import java.util.Objects;

public abstract class TestBase {
    public Collection<String> getInputData(int day, String task) {
        return IOUtil.readLines(Objects.requireNonNull(getClass().getResourceAsStream("/day" + day + "/input" + task + ".txt")));
    }
}
