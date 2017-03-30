package com.mf.stock.model;

import com.mf.stock.R;

public enum OrderState {

    ON_PREPARATION(R.string.order_state_on_preparation),
    DONE(R.string.order_state_done),
    READY_TO_PREPARE(R.string.order_state_ready_to_preparation);

    private int key;

    OrderState(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
