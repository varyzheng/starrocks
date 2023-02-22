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

import com.starrocks.analysis.Expr;
import com.starrocks.analysis.TableName;
import com.starrocks.catalog.Table;
import com.starrocks.sql.parser.NodePosition;

import java.util.List;

public class UpdateStmt extends DmlStmt {
    private final TableName tableName;
    private final List<ColumnAssignment> assignments;
    private final List<Relation> fromRelations;
    private final Expr wherePredicate;
    private final List<CTERelation> commonTableExpressions;

    private Table table;
    private QueryStatement queryStatement;

    private boolean nullExprInAutoIncrement;

    public UpdateStmt(TableName tableName, List<ColumnAssignment> assignments, List<Relation> fromRelations,
                      Expr wherePredicate, List<CTERelation> commonTableExpressions) {
        this(tableName, assignments, fromRelations, wherePredicate, commonTableExpressions, NodePosition.ZERO);
    }

    public UpdateStmt(TableName tableName, List<ColumnAssignment> assignments, List<Relation> fromRelations,
                      Expr wherePredicate, List<CTERelation> commonTableExpressions, NodePosition pos) {
        super(pos);
        this.tableName = tableName;
        this.assignments = assignments;
        this.fromRelations = fromRelations;
        this.wherePredicate = wherePredicate;
        this.commonTableExpressions = commonTableExpressions;
        this.nullExprInAutoIncrement = true;
    }

    @Override
    public TableName getTableName() {
        return tableName;
    }

    public void setNullExprInAutoIncrement(boolean nullExprInAutoIncrement) {
        this.nullExprInAutoIncrement = nullExprInAutoIncrement;
    }

    public boolean nullExprInAutoIncrement() {
        return nullExprInAutoIncrement;
    }

    public List<ColumnAssignment> getAssignments() {
        return assignments;
    }

    public List<Relation> getFromRelations() {
        return fromRelations;
    }

    public Expr getWherePredicate() {
        return wherePredicate;
    }

    public List<CTERelation> getCommonTableExpressions() {
        return commonTableExpressions;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Table getTable() {
        return table;
    }

    public void setQueryStatement(QueryStatement queryStatement) {
        this.queryStatement = queryStatement;
    }

    public QueryStatement getQueryStatement() {
        return queryStatement;
    }

    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitUpdateStatement(this, context);
    }
}
