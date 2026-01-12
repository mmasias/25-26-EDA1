~~Vamos a plantear los pedidos como una cola. Lo ideal sería que dependiera el peso de: Lo que tarde en ser preparado (tiempo de preparación) y lo que tarde en ser entregado (diferencia entre la hora de pedido y la hora de llegada --se puede estimar--).~~ **Por ahora sale de nuestros terminos**

[![](https://mermaid.ink/img/pako:eNp1VOtu2kgUfpXRbColEiTGQKBWmlUWnC7aBhBQdbU4Wo3Hx2G2w4w1HjdJKT_6DH2CfYD91QeoVF6sxza3CtZ_PJfvfOc7t1lQriOgHo2lfuQzZiyZdANF8Euz8MGwZEYCeve23x0Q_8-JP-oNRgEtAfnXh8eBicBM30h4YGQIkYg06WfwQd-XKFBRoA4Yx73xxL-7IV2fvPbHk97qS5-c_pZxCSTRhtwJlVl9tu9pbFEcnp-e9pTgQm8wZzvIbvXiBbnhNmNSfGRcrL4qEgGZCJgnOt2h3iYRs_COCTu9Cq-HLNWk5hFffYB_gIu5AGX11UV4fRWai-ue4gbmeMQMmZz7aQKGIWtxNxl0B2MidUqSIgEpRk24luz-f8T1VAqmEHaYSlKtXpcAOy1_6BH5pEht7hF9qCjXBukefYksbHdhHWbvAPCTrBHw1b-SZ1LnfoZGaCMiFh3LWEGEeCb5ovxlEnUOIdXp8ljUJSg3-3STSMERfLv6auZo9ol08KqPjTgN6MnJkLwik78XiYFkSark9A8SBDzSNj-EIu_Ls5OTgB5N7oZprS_MhIx-B5ZsK-x6eAypNRk2iGFmW-ARVDXmX6EwyUgHq3ex-mxCLYvbkKUMOxvrAJKovL-LWMnw_niwW8eFkM4M4lsDsPj-raO5UGA0eSNCA78ezdUGjrZkvPqvoBjqBOs39Z-sYWA2k8a1KuRhdHd-fzBai8qDuj_O19elIq0sDhBM1wuMulDGVKSPhlT6X9vq99MSbY5MuX3GKd7WIRZSer_EcfySNyqYdv0ecBtyx43W2-qjiOzMc5OnzUHEUnyKDHv2SJM092n3E1syQy1uxrBldmrN1svwgHmfY6-JN-I4bztbCrhs1hzngIJW6AOOA_WwdaBC52DmLN_SRU4eUDvD5yGgHi4jiFkmbf58LdEsYeovrecbS6Ozhxn1YiZT3GWFmq5g-DTuIJhPMB2dKUu9husWHNRb0Cfq1RrNc9dtt1tOy3Vajct6hT5Tr9qoX5636hh-vd6ut9pOrb6s0I-F29p502030aJVa7uOc9luLH8A77relA?type=png)](https://mermaid.live/edit#pako:eNp1VOtu2kgUfpXRbColEiTGQKBWmlUWnC7aBhBQdbU4Wo3Hx2G2w4w1HjdJKT_6DH2CfYD91QeoVF6sxza3CtZ_PJfvfOc7t1lQriOgHo2lfuQzZiyZdANF8Euz8MGwZEYCeve23x0Q_8-JP-oNRgEtAfnXh8eBicBM30h4YGQIkYg06WfwQd-XKFBRoA4Yx73xxL-7IV2fvPbHk97qS5-c_pZxCSTRhtwJlVl9tu9pbFEcnp-e9pTgQm8wZzvIbvXiBbnhNmNSfGRcrL4qEgGZCJgnOt2h3iYRs_COCTu9Cq-HLNWk5hFffYB_gIu5AGX11UV4fRWai-ue4gbmeMQMmZz7aQKGIWtxNxl0B2MidUqSIgEpRk24luz-f8T1VAqmEHaYSlKtXpcAOy1_6BH5pEht7hF9qCjXBukefYksbHdhHWbvAPCTrBHw1b-SZ1LnfoZGaCMiFh3LWEGEeCb5ovxlEnUOIdXp8ljUJSg3-3STSMERfLv6auZo9ol08KqPjTgN6MnJkLwik78XiYFkSark9A8SBDzSNj-EIu_Ls5OTgB5N7oZprS_MhIx-B5ZsK-x6eAypNRk2iGFmW-ARVDXmX6EwyUgHq3ex-mxCLYvbkKUMOxvrAJKovL-LWMnw_niwW8eFkM4M4lsDsPj-raO5UGA0eSNCA78ezdUGjrZkvPqvoBjqBOs39Z-sYWA2k8a1KuRhdHd-fzBai8qDuj_O19elIq0sDhBM1wuMulDGVKSPhlT6X9vq99MSbY5MuX3GKd7WIRZSer_EcfySNyqYdv0ecBtyx43W2-qjiOzMc5OnzUHEUnyKDHv2SJM092n3E1syQy1uxrBldmrN1svwgHmfY6-JN-I4bztbCrhs1hzngIJW6AOOA_WwdaBC52DmLN_SRU4eUDvD5yGgHi4jiFkmbf58LdEsYeovrecbS6Ozhxn1YiZT3GWFmq5g-DTuIJhPMB2dKUu9husWHNRb0Cfq1RrNc9dtt1tOy3Vajct6hT5Tr9qoX5636hh-vd6ut9pOrb6s0I-F29p502030aJVa7uOc9luLH8A77relA)

~~**La Fórmula del Peso (El "Score")** Propuesto por: [Gemini](https://gemini.google.com/share/d44ffa9c05ca)~~

~~Necesitamos un valor numérico que represente la urgencia. ~~

~~Queremos que:~~
~~ - Si el tiempo de preparación ($T_{prep}$) es bajo, la urgencia sube.~~
~~ - Si el tiempo de espera ($T_{espera}$) es alto, la urgencia sube.~~

~~Como en una cola de prioridad solemos extraer el menor valor, podemos plantear esta ecuación para el "Peso" ($P$):~~

~~$$P = T_{prep} - (K \times T_{espera})$$~~

~~$T_{prep}$: Fijo. (Ej: 8 min para ensalada).~~

~~$T_{espera}$: Variable. Crece cada minuto.~~

~~$K$: Factor de Justicia. Es una constante que tú decides.~~

~~Si $K = 0.5$, cada 2 minutos de espera "descuentan" 1 minuto de coste de preparación.Una ensalada (8 min) que lleva esperando 10 minutos tendría un peso de: $8 - (0.5 \times 10) = 3$. ¡Ahora compite con los cafés!~~