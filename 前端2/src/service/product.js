import httpMethod from "./httpMethod";
export default {
  insert(data) {
    return httpMethod({
      url: "/products",
      data,
      method: "POST",
    });
  },
  update(data, id) {
    return httpMethod({
      url: "/products/" + id,
      data,
      method: "PUT",
    });
  },
  delete(id) {
    return httpMethod({
      url: "/products/" + id,
      method: "DELETE",
    });
  },
  get(id) {
    return httpMethod({
      url: "/products/" + id,
      method: "GET",
    });
  },
  getAll(page, size,formState) {
    return httpMethod({
      url: "/products" + `?offset=${page}&limit=${size}&category=${formState}`,

      method: "GET",
    });
  },
};