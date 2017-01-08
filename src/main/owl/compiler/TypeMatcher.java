/*
 * Copyright 2016 Igor Demura
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package owl.compiler;

final class TypeMatcher {
    final static class ParamMatcher {
        boolean match(AstType type, ErrorListener errorListener) {
            if (!(type.abstractType instanceof AstScalarType)) {
                errorListener.error("type matcher only supports scalar types");
                return false;
            }
            return true;
        }
    }

    static boolean run(AstType type, NameMap<AstAbstractType> abstractTypes, ErrorListener errorListener) {
        return type.abstractType != null || new TypeMatcher(abstractTypes, errorListener).resolve(type);
    }

    private final NameMap<AstAbstractType> abstractTypes;
    private final ErrorListener errorListener;

    private TypeMatcher(NameMap<AstAbstractType> abstractTypes, ErrorListener errorListener) {
        this.abstractTypes = abstractTypes;
        this.errorListener = errorListener;
    }

    private boolean resolve(AstType type) {
        AstAbstractType at = abstractTypes.get(type.name);
        if (at == null) {
            errorListener.error(type.getLine(), type.getCharPosition(),
                    "type " + type.name + " not found");
            return false;
        }
        for (AstType a : type.args) {
            if (!resolve(a)) {
                return false;
            }
        }
        if (at.getParamMatchers().size() != type.args.size()) {
            errorListener.error(type.getLine(), type.getCharPosition(),
                    "abstract type " + at.getName() + " takes " + at.getParamMatchers().size() + " params, " +
                    type.args.size() + " given");
            return false;
        }
        for (int i = 0; i < type.args.size(); i++) {
            if (!at.getParamMatchers().get(i).match(type.args.get(i), errorListener)) {
                return false;
            }
        }
        type.abstractType = at;
        return true;
    }
}
