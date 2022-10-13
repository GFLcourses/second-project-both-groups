package com.gfl.resources_server.service.mapper;

import java.util.List;

public interface Mapper<T, F> {
    T map(F from);

    List<T> map(List<F> from);
}
