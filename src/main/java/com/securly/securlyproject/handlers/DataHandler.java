package com.securly.securlyproject.handlers;

import com.securly.securlyproject.data.model.Sync;

public interface DataHandler {
    void apply(Sync sync);
}