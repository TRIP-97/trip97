package com.trip97.infra.util;

import com.trip97.modules.user.model.oauth.OAuthProvider;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(OAuthProvider.class)
public class OAuthProviderTypeHandler implements TypeHandler<OAuthProvider> {

    /**
     * 지정된 타입의 어떤 값을 DB에 저장할것인지 정하는 메서드
     */
    @Override
    public void setParameter(PreparedStatement ps, int i, OAuthProvider parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getKey());
    }

    /**
     *  컬럼 이름 기반으로 조회된 값을 활용해서 실제 반환할 객체 구성하기
     */
    @Override
    public OAuthProvider getResult(ResultSet rs, String columnName) throws SQLException {
        String oAuthProviderKey = rs.getString(columnName);
        return getOAuthProvider(oAuthProviderKey);
    }

    /**
     *  컬럼 index 기반으로 조회된 값을 활용해서 실제 반환할 객체 구성하기
     */
    @Override
    public OAuthProvider getResult(ResultSet rs, int columnIndex) throws SQLException {
        String oAuthProviderKey = rs.getString(columnIndex);
        return getOAuthProvider(oAuthProviderKey);
    }

    /**
     *  Callablestatement에서 컬럼 index 기반으로 조회된 값을 활용해서 실제 반환할 객체 구성하기
     */
    @Override
    public OAuthProvider getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String oAuthProviderKey = cs.getString(columnIndex);
        return getOAuthProvider(oAuthProviderKey);
    }

    /**
     * 실제 객체를 구성하는 메서드
     */
    private OAuthProvider getOAuthProvider(String oAuthProviderKey) {
        OAuthProvider oAuthProvider = null;
        switch (oAuthProviderKey) {
            case "OAUTH_KAKAO":
                oAuthProvider = OAuthProvider.KAKAO;
                break;
            case "OAUTH_NAVER":
                oAuthProvider = OAuthProvider.NAVER;
                break;
        }
        return oAuthProvider;
    }
}
