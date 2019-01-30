/**
 *    Copyright 2016-2017 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.dynamic.sql;

import java.util.Objects;
import java.util.function.Supplier;

public abstract class AbstractTwoValueCondition<T> implements VisitableCondition<T> {
    private Supplier<T> valueSupplier1;
    private Supplier<T> valueSupplier2;
    
    protected AbstractTwoValueCondition(Supplier<T> valueSupplier1, Supplier<T> valueSupplier2) {
        this.valueSupplier1 = Objects.requireNonNull(valueSupplier1);
        this.valueSupplier2 = Objects.requireNonNull(valueSupplier2);
    }

    public T value1() {
        return valueSupplier1.get();
    }

    public T value2() {
        return valueSupplier2.get();
    }

    @Override
    public <R> R accept(ConditionVisitor<T,R> visitor) {
        return visitor.visit(this);
    }

    public abstract String renderCondition(String columnName, String placeholder1, String placeholder2);
}
