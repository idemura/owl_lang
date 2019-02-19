package owl.compiler;

import java.util.List;

final class TypeResolver {
    final static class ParamMatcher {
        boolean match(AstType type, ErrorListener errorListener) {
            if (!(type.abstractType instanceof AstScalar)) {
                errorListener.error("type matcher only supports scalar types");
                return false;
            }
            return true;
        }
    }

    static boolean run(AstType type, NameMap<AstAbstractType> abstractTypes, ErrorListener errorListener) {
        return type.abstractType != null || new TypeResolver(abstractTypes, errorListener).resolve(type);
    }

    private final NameMap<AstAbstractType> abstractTypes;
    private final ErrorListener errorListener;

    private TypeResolver(NameMap<AstAbstractType> abstractTypes, ErrorListener errorListener) {
        this.abstractTypes = abstractTypes;
        this.errorListener = errorListener;
    }

    private boolean resolve(AstType type) {
        if (type.isGeneric()) {
            return true;
        }
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
        List<TypeParam> params = at.getTypeParams();
        if (params.size() != type.args.size()) {
            errorListener.error(type.getLine(), type.getCharPosition(),
                    "abstract type " + at.getName() + " takes " + params.size() + " params, " +
                    type.args.size() + " given");
            return false;
        }
        for (int i = 0; i < type.args.size(); i++) {
            if (!params.get(i).match(type.args.get(i))) {
                errorListener.error(type.getLine(), type.getCharPosition(),
                        "type " + type.args.get(i) + " doesn't match " + params.get(i));
                return false;
            }
        }
        type.abstractType = at;
        return true;
    }
}
