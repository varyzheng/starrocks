// Copyright 2021-present StarRocks, Inc. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.


package com.starrocks.sql.ast;

import com.starrocks.catalog.MaterializedView;
import com.starrocks.sql.parser.NodePosition;

import java.time.LocalDateTime;

public class AsyncRefreshSchemeDesc extends RefreshSchemeClause {

    private boolean defineStartTime;

    private LocalDateTime startTime;

    private IntervalLiteral intervalLiteral;

    public AsyncRefreshSchemeDesc(boolean defineStartTime, LocalDateTime startTime, IntervalLiteral intervalLiteral,
                                  MaterializedView.RefreshMoment moment, NodePosition pos) {
        super(MaterializedView.RefreshType.ASYNC, pos, moment);
        this.defineStartTime = defineStartTime;
        this.startTime = startTime;
        this.intervalLiteral = intervalLiteral;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public IntervalLiteral getIntervalLiteral() {
        return intervalLiteral;
    }

    public boolean isDefineStartTime() {
        return defineStartTime;
    }
}

